package io.github.yvasyliev.dz4j.util;

import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import lombok.experimental.UtilityClass;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Utility class providing suppliers for access tokens.
 */
@UtilityClass
public class AccessTokenSuppliers {
    @SuppressWarnings("checkstyle:MemberName")
    private final CompletableFuture<AccessToken> EMPTY_ACCESS_TOKEN_FUTURE =
            CompletableFuture.completedFuture(new AccessToken());

    /**
     * A supplier that always returns an empty access token future.
     */
    @SuppressWarnings({"checkstyle:VisibilityModifier", "checkstyle:MemberName"})
    public final Supplier<CompletableFuture<AccessToken>> EMPTY_ACCESS_TOKEN_SUPPLIER =
            () -> EMPTY_ACCESS_TOKEN_FUTURE;

    /**
     * A factory that always returns an empty access token future, regardless of the provided
     * {@link OAuthRequestFactory}.
     */
    @SuppressWarnings({"checkstyle:VisibilityModifier", "checkstyle:MemberName"})
    public final Function<OAuthRequestFactory, CompletableFuture<AccessToken>> EMPTY_ACCESS_TOKEN_SUPPLIER_FACTORY =
            oauth -> EMPTY_ACCESS_TOKEN_FUTURE;

    //region suppliers

    /**
     * Creates a supplier that always returns the provided access token wrapped in a completed future.
     *
     * @param accessToken the access token to be supplied
     * @return a supplier that always returns the provided access token wrapped in a completed future
     */
    public Supplier<CompletableFuture<AccessToken>> accessTokenSupplier(AccessToken accessToken) {
        var accessTokenFuture = CompletableFuture.completedFuture(accessToken);

        return () -> accessTokenFuture;
    }

    /**
     * Creates a supplier that retrieves an access token asynchronously using the provided parameters and the given
     * {@link OAuthRequestFactory}.
     *
     * @param oauth  the {@link OAuthRequestFactory} to be used for retrieving the access token
     * @param appId  the application ID to be used for retrieving the access token
     * @param secret the application secret to be used for retrieving the access token
     * @param code   the authorization code to be used for retrieving the access token
     * @return a supplier that retrieves an access token asynchronously using the provided parameters and the given
     *         {@link OAuthRequestFactory}
     */
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

    /**
     * Creates a factory that always returns the provided access token wrapped in a completed future, regardless of the
     * provided {@link OAuthRequestFactory}.
     *
     * @param accessToken the access token to be supplied
     * @return a factory that always returns the provided access token wrapped in a completed future, regardless of the
     *         provided {@link OAuthRequestFactory}
     */
    public Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory(
            AccessToken accessToken
    ) {
        var accessTokenFuture = accessTokenSupplier(accessToken).get();

        return oauth -> accessTokenFuture;
    }

    /**
     * Creates a factory that retrieves an access token asynchronously using the provided parameters and the given
     * {@link OAuthRequestFactory}.
     *
     * @param appId  the application ID to be used for retrieving the access token
     * @param secret the application secret to be used for retrieving the access token
     * @param code   the authorization code to be used for retrieving the access token
     * @return a factory that retrieves an access token asynchronously using the provided parameters and the given
     */
    public Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory(
            int appId,
            String secret,
            String code
    ) {
        return oauth -> accessTokenSupplier(oauth, appId, secret, code).get();
    }

    //endregion
}
