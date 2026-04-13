package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.factory.InfosRequestFactory;
import io.github.yvasyliev.dz4j.model.Infos;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadTokenManagerTest {
    @InjectMocks
    @Spy
    private UploadTokenManager uploadTokenManager;

    @Mock
    private InfosRequestFactory infos;

    @Test
    void shouldCacheInfos() {
        var expected = Infos.builder()
                .uploadToken("token")
                .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                .build();
        var request = Mockito.<DeezerRequest<Infos>>mock();

        when(infos.getInfos()).thenReturn(request);
        when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isCompletedWithValue(expected);

        actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isCompletedWithValue(expected);
        verify(infos, times(1)).getInfos();
    }

    @Test
    void shouldReturnCachedInfosOnUncompletedFuture() {
        var expected = Mockito.<CompletableFuture<Infos>>mock();
        var request = Mockito.<DeezerRequest<Infos>>mock();

        when(infos.getInfos()).thenReturn(request);
        when(request.executeAsync()).thenReturn(expected);

        var actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isSameAs(expected);

        actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isSameAs(expected);
        verify(infos, times(1)).getInfos();
    }

    @Test
    void shouldRefreshInfosOnCompletionException() {
        var expected = Infos.builder()
                .uploadToken("token")
                .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                .build();
        var request = Mockito.<DeezerRequest<Infos>>mock();

        when(infos.getInfos()).thenReturn(request);
        when(request.executeAsync()).thenReturn(CompletableFuture.failedFuture(mock()));

        var actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isCompletedExceptionally();

        when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(expected));

        actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isCompletedWithValue(expected);
    }

    @Test
    void shouldRefreshInfosOnExpiredUploadToken() {
        var expiredInfos = Infos.builder()
                .uploadToken("expired-token")
                .uploadTokenExpiresAt(Instant.now().minusSeconds(3600))
                .build();
        var expected = Infos.builder()
                .uploadToken("fresh-token")
                .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                .build();
        var request = Mockito.<DeezerRequest<Infos>>mock();

        when(infos.getInfos()).thenReturn(request);
        when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(expiredInfos));

        var actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isCompletedWithValue(expiredInfos);

        when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(expected));

        actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isCompletedWithValue(expected);
    }

    @Test
    void shouldReturnCachedTokenWhenItWasFetchConcurrently() {
        var expected = Infos.builder()
                .uploadToken("token")
                .uploadTokenExpiresAt(Instant.now())
                .build();
        var future = CompletableFuture.completedFuture(expected);
        var request = Mockito.<DeezerRequest<Infos>>mock();
        var validationCount = new AtomicInteger(0);

        when(infos.getInfos()).thenReturn(request);
        when(request.executeAsync()).thenReturn(future);
        when(uploadTokenManager.isInvalid(any())).thenCallRealMethod();
        when(uploadTokenManager.isInvalid(future)).thenAnswer(invocation -> validationCount.getAndIncrement() == 0);

        var actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isCompletedWithValue(expected);

        actual = uploadTokenManager.getUploadToken();

        assertThat(actual).isCompletedWithValue(expected);
        verify(infos, times(1)).getInfos();
    }
}
