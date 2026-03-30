package io.github.yvasyliev.dz4j;

import io.github.yvasyliev.dz4j.authorization.Authorization;
import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.COMPLETABLE_FUTURE;
import static org.assertj.core.api.InstanceOfAssertFactories.type;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class DeezerClientTest {
    private static final AccessToken ACCESS_TOKEN = new AccessToken("token", Instant.now());

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> shouldCreateDefaultDeezerClient = () -> Stream.of(
            arguments("constructor", new DeezerClient()),
            arguments("builder", DeezerClient.builder().build())
    );

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> shouldAuthorizeClient = () -> {
        var authorization = Authorization.of(ACCESS_TOKEN);
        var stringAccessTokenClient = new DeezerClient();
        stringAccessTokenClient.authorization(authorization);

        return Stream.of(
                arguments("constructor", new DeezerClient(authorization)),
                arguments("builder", DeezerClient.builder().authorization(authorization).build()),
                arguments("method", stringAccessTokenClient)
        );
    };

    @ParameterizedTest(name = "should create default DeezerClient by {0}")
    @FieldSource
    void shouldCreateDefaultDeezerClient(String ignored, DeezerClient deezerClient) {
        assertThat(deezerClient).hasNoNullFieldsOrProperties();
        assertAccessToken(deezerClient, null);
    }

    @ParameterizedTest(name = "should authorize DeezerClient by {0}")
    @FieldSource
    void shouldAuthorizeClient(String ignored, DeezerClient deezerClient) {
        assertAccessToken(deezerClient, ACCESS_TOKEN);
    }

    @Test
    void shouldClearAuthorization() {
        var deezerClient = new DeezerClient(Authorization.of(ACCESS_TOKEN));

        deezerClient.removeAuthorization();

        assertAccessToken(deezerClient, null);
    }

    private void assertAccessToken(DeezerClient deezerClient, @Nullable AccessToken expected) {
        assertThat(deezerClient)
                .extracting("authorizationManager", type(AuthorizationManager.class))
                .extracting(AuthorizationManager::getToken, COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }
}
