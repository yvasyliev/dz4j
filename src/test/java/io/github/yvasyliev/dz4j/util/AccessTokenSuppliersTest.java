package io.github.yvasyliev.dz4j.util;

import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.COMPLETABLE_FUTURE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessTokenSuppliersTest {
    @Test
    void shouldReturnEmptyAccessTokenSupplier() {
        var expected = new AccessToken();
        var actual = AccessTokenSuppliers.EMPTY_ACCESS_TOKEN_SUPPLIER;

        assertThat(actual)
                .extracting(Supplier::get, COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }

    @Test
    void shouldReturnEmptyAccessTokenSupplierFactory() {
        var expected = new AccessToken();
        var actual = AccessTokenSuppliers.EMPTY_ACCESS_TOKEN_SUPPLIER_FACTORY;

        assertThat(actual)
                .extracting(factory -> factory.apply(mock()), COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }

    @Test
    void shouldReturnAccessTokenSupplierFromAccessToken() {
        var expected = new AccessToken("token", Instant.now());
        var actual = AccessTokenSuppliers.accessTokenSupplier(expected);

        assertThat(actual)
                .extracting(Supplier::get, COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }

    @Test
    void shouldReturnAccessTokenSupplierFromAppIdSecretAndCode() {
        var oauth = mock(OAuthRequestFactory.class);
        var appId = 123;
        var secret = "secret";
        var code = "code";
        var request = Mockito.<DeezerRequest<AccessToken>>mock();
        var expected = new AccessToken("token", Instant.now());

        when(oauth.getAccessToken(appId, secret, code)).thenReturn(request);
        when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = AccessTokenSuppliers.accessTokenSupplier(oauth, appId, secret, code);

        assertThat(actual)
                .extracting(Supplier::get, COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }

    @Test
    void shouldReturnAccessTokenSupplierFactoryFromAccessToken() {
        var expected = new AccessToken("token", Instant.now());
        var actual = AccessTokenSuppliers.accessTokenSupplierFactory(expected);

        assertThat(actual)
                .extracting(factory -> factory.apply(mock()), COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }

    @Test
    void shouldReturnAccessTokenSupplierFactoryFromAppIdSecretAndCode() {
        var oauth = mock(OAuthRequestFactory.class);
        var appId = 123;
        var secret = "secret";
        var code = "code";
        var request = Mockito.<DeezerRequest<AccessToken>>mock();
        var expected = new AccessToken("token", Instant.now());

        when(oauth.getAccessToken(appId, secret, code)).thenReturn(request);
        when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = AccessTokenSuppliers.accessTokenSupplierFactory(appId, secret, code);

        assertThat(actual)
                .extracting(factory -> factory.apply(oauth), COMPLETABLE_FUTURE)
                .isCompletedWithValue(expected);
    }
}
