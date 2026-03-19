package io.github.yvasyliev.deezer.util;

import io.github.yvasyliev.deezer.factory.OAuthRequestFactory;
import io.github.yvasyliev.deezer.model.AccessToken;
import lombok.experimental.UtilityClass;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

@UtilityClass
public class AccessTokenSuppliers {
    private final CompletableFuture<AccessToken> EMPTY_ACCESS_TOKEN_FUTURE =
            CompletableFuture.completedFuture(new AccessToken());
    public final Supplier<CompletableFuture<AccessToken>> EMPTY_ACCESS_TOKEN_SUPPLIER =
            () -> EMPTY_ACCESS_TOKEN_FUTURE;
    public final Function<OAuthRequestFactory, CompletableFuture<AccessToken>> EMPTY_ACCESS_TOKEN_SUPPLIER_FACTORY =
            oauth -> EMPTY_ACCESS_TOKEN_FUTURE;

    //region suppliers

    public Supplier<CompletableFuture<AccessToken>> accessTokenSupplier(AccessToken accessToken) {
        var accessTokenFuture = CompletableFuture.completedFuture(accessToken);

        return () -> accessTokenFuture;
    }

    public Supplier<CompletableFuture<AccessToken>> accessTokenSupplier(
            OAuthRequestFactory oauth,
            int appId,
            String secret,
            String code
    ) {
        return () -> oauth.getAccessToken(appId, secret, code).executeAsync();
    }

    //endregion

    //region factories

    public Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory(
            AccessToken accessToken
    ) {
        var accessTokenFuture = accessTokenSupplier(accessToken).get();

        return oauth -> accessTokenFuture;
    }

    public Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory(
            int appId,
            String secret,
            String code
    ) {
        return oauth -> accessTokenSupplier(oauth, appId, secret, code).get();
    }

    //endregion
}
