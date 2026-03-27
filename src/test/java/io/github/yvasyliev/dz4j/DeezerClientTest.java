package io.github.yvasyliev.dz4j;

import io.github.yvasyliev.dz4j.authorization.AccessTokenSupplier;
import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.util.AccessTokenSuppliers;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.COMPLETABLE_FUTURE;
import static org.assertj.core.api.InstanceOfAssertFactories.type;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class DeezerClientTest {
    private static final String TOKEN = "token";
    private static final AccessToken STATIC_ACCESS_TOKEN = new AccessToken(TOKEN);
    private static final AccessToken ACCESS_TOKEN = new AccessToken(TOKEN, Instant.now());
    private static final int APP_ID = 123;
    private static final String SECRET = "secret";
    private static final String CODE = "code";

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> shouldCreateDefaultDeezerClient = () -> Stream.of(
            arguments("constructor", new DeezerClient()),
            arguments("builder", DeezerClient.builder().build())
    );

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> shouldAuthorizeWithStringAccessToken = () -> {
        var stringAccessTokenClient = new DeezerClient();
        stringAccessTokenClient.authorization(TOKEN);

        return Stream.of(
                arguments("constructor", new DeezerClient(TOKEN)),
                arguments("builder", DeezerClient.builder().authorization(TOKEN).build()),
                arguments("method", stringAccessTokenClient)
        );
    };

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> shouldAuthorizeWithAccessToken = () -> {
        var stringAccessTokenClient = new DeezerClient();
        stringAccessTokenClient.authorization(ACCESS_TOKEN);

        return Stream.of(
                arguments("constructor", new DeezerClient(ACCESS_TOKEN)),
                arguments("builder", DeezerClient.builder().authorization(ACCESS_TOKEN).build()),
                arguments("method", stringAccessTokenClient)
        );
    };

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> shouldAuthorizeWithAppIdSecretAndCode = () -> {
        var accessTokenFuture = CompletableFuture.completedFuture(ACCESS_TOKEN);
        var appIdSecretCodeClient = new DeezerClient();
        @Cleanup var accessTokenSuppliers = mockStatic(AccessTokenSuppliers.class);

        accessTokenSuppliers.when(() -> AccessTokenSuppliers.accessTokenSupplier(
                any(),
                eq(APP_ID),
                eq(SECRET),
                eq(CODE)
        )).thenReturn((Supplier<CompletableFuture<AccessToken>>) () -> accessTokenFuture);
        accessTokenSuppliers.when(() -> AccessTokenSuppliers.accessTokenSupplierFactory(
                eq(APP_ID),
                eq(SECRET),
                eq(CODE)
        )).thenReturn((Function<OAuthRequestFactory, CompletableFuture<AccessToken>>) oAuthRequestFactory ->
                accessTokenFuture
        );

        appIdSecretCodeClient.authorization(APP_ID, SECRET, CODE);

        return Stream.of(
                arguments("constructor", new DeezerClient(APP_ID, SECRET, CODE)),
                arguments("builder", DeezerClient.builder().authorization(APP_ID, SECRET, CODE).build()),
                arguments("method", appIdSecretCodeClient)
        );
    };

    @ParameterizedTest(name = "should create default DeezerClient by {0}")
    @FieldSource
    void shouldCreateDefaultDeezerClient(String ignored, DeezerClient deezerClient) {
        assertThat(deezerClient).hasNoNullFieldsOrProperties();
        assertAccessToken(deezerClient, new AccessToken());
    }

    @ParameterizedTest(name = "should authorize with string access token by {0}")
    @FieldSource
    void shouldAuthorizeWithStringAccessToken(String ignored, DeezerClient deezerClient) {
        assertAccessToken(deezerClient, STATIC_ACCESS_TOKEN);
    }

    @ParameterizedTest(name = "should authorize with access token by {0}")
    @FieldSource
    void shouldAuthorizeWithAccessToken(String ignored, DeezerClient deezerClient) {
        assertAccessToken(deezerClient, ACCESS_TOKEN);
    }

    @ParameterizedTest(name = "should authorize with appId, secret and code by {0}")
    @FieldSource
    void shouldAuthorizeWithAppIdSecretAndCode(String ignored, DeezerClient deezerClient) {
        assertAccessToken(deezerClient, ACCESS_TOKEN);
    }

    @Test
    void shouldRemoveAuthorization() {
        var deezerClient = new DeezerClient(ACCESS_TOKEN);

        deezerClient.removeAuthorization();

        assertAccessToken(deezerClient, new AccessToken());
    }

    private void assertAccessToken(DeezerClient deezerClient, AccessToken expected) {
        assertThat(deezerClient)
                .extracting("accessTokenSupplier", type(AccessTokenSupplier.class))
                .extracting(AccessTokenSupplier::get, COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }
}
