package io.github.yvasyliev.dz4j.util;

import io.github.yvasyliev.dz4j.authorization.TokenManager;
import io.github.yvasyliev.dz4j.factory.InfosRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Infos;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.COMPLETABLE_FUTURE;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenManagersTest {
    @Test
    @SuppressWarnings("DuplicateAssertion")
    void testAccessTokenManager() {
        var expected = "token";
        var accessTokenManager = TokenManagers.accessTokenManager(
                () -> CompletableFuture.completedFuture(new AccessToken(expected, Instant.now()))
        );

        assertThat(accessTokenManager)
                .extracting(TokenManager::getToken, COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
        assertThat(accessTokenManager)
                .extracting(TokenManager::getToken, COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }

    @Nested
    class UploadTokenManagerTest {
        @Mock private InfosRequestFactory infosRequestFactory;
        @Mock private DeezerRequest<Infos> request;

        @BeforeEach
        void setUp() {
            when(infosRequestFactory.getInfos()).thenReturn(request);
        }

        @Test
        void shouldReturnOldTokenIfTokenIsNotReady() {
            var expected = "token";
            var infos = Infos.builder()
                    .uploadToken(expected)
                    .uploadTokenExpiresAt(Instant.now())
                    .build();
            var uploadTokenFuture = spy(CompletableFuture.completedFuture(infos));
            var uploadTokenManager = TokenManagers.uploadTokenManager(infosRequestFactory);

            when(request.executeAsync()).thenReturn(uploadTokenFuture);

            assertUploadToken(uploadTokenManager, expected);

            when(uploadTokenFuture.isDone()).thenReturn(false);

            assertUploadToken(uploadTokenManager, expected);
        }

        @Test
        void shouldReturnOldTokenIfTokenIsNotExpired() {
            var expected = "token";
            var infos = Infos.builder()
                    .uploadToken("token")
                    .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                    .build();
            var uploadTokenManager = TokenManagers.uploadTokenManager(infosRequestFactory);

            when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(infos));

            assertUploadToken(uploadTokenManager, expected);
            assertUploadToken(uploadTokenManager, expected);
        }

        @Test
        void shouldReturnNewTokenIfOldTokenIsFailed() {
            var expected = "token";
            var infos = Infos.builder()
                    .uploadToken("token")
                    .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                    .build();
            var uploadTokenManager = TokenManagers.uploadTokenManager(infosRequestFactory);

            when(request.executeAsync())
                    .thenReturn(CompletableFuture.failedFuture(new RuntimeException("test exception")));

            assertThat(uploadTokenManager)
                    .extracting(TokenManager::getToken, COMPLETABLE_FUTURE)
                    .isCompletedExceptionally();

            when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(infos));

            assertUploadToken(uploadTokenManager, expected);
        }

        @Test
        void shouldReturnNewTokenIfOldTokenIsExpired() {
            var oldToken = "old-token";
            var oldInfos = Infos.builder()
                    .uploadToken(oldToken)
                    .uploadTokenExpiresAt(Instant.now().minusSeconds(3600))
                    .build();
            var expected = "expected-token";
            var newInfos = Infos.builder()
                    .uploadToken("expected-token")
                    .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                    .build();
            var uploadTokenManager = TokenManagers.uploadTokenManager(infosRequestFactory);

            when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(oldInfos));

            assertUploadToken(uploadTokenManager, oldToken);

            when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(newInfos));

            assertUploadToken(uploadTokenManager, expected);
        }

        private void assertUploadToken(TokenManager<Infos> uploadTokenManager, String expected) {
            assertThat(uploadTokenManager)
                    .extracting(TokenManager::getToken, COMPLETABLE_FUTURE)
                    .isCompletedWithValue(expected);
        }
    }
}
