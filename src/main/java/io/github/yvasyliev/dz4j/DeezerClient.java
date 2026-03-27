package io.github.yvasyliev.dz4j;

import feign.AsyncFeign;
import io.github.yvasyliev.dz4j.authorization.AccessTokenSupplier;
import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.feign.DeezerFeignBuilder;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.util.AccessTokenSuppliers;
import io.github.yvasyliev.dz4j.configuration.BaseUrls;
import io.github.yvasyliev.dz4j.util.Customizer;
import io.github.yvasyliev.dz4j.factory.RequestFactoryProvider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The main client class for interacting with the Deezer API.
 *
 * <p>
 * It provides methods for authorization and access to various services. The client can be configured with custom base
 * URLs, Feign configurations, and access token suppliers. It also supports dynamic authorization by allowing the access
 * token to be updated at runtime.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeezerClient {
    @Delegate
    private final RequestFactoryProvider requestFactoryProvider;
    private final AccessTokenSupplier accessTokenSupplier;

    //region constructors

    /**
     * Creates a new instance of {@link DeezerClient} without any authorization.
     */
    public DeezerClient() {
        this(new AccessToken());
    }

    /**
     * Creates a new instance of {@link DeezerClient} with the provided raw access token string for authorization.
     *
     * @param accessToken the raw access token string to be used for authorization
     */
    public DeezerClient(String accessToken) {
        this(new AccessToken(accessToken));
    }

    /**
     * Creates a new instance of {@link DeezerClient} with the provided {@link AccessToken} for authorization.
     *
     * @param accessToken the {@link AccessToken} object to be used for authorization
     */
    public DeezerClient(AccessToken accessToken) {
        this(AccessTokenSuppliers.accessTokenSupplierFactory(accessToken));
    }

    /**
     * Creates a new instance of {@link DeezerClient} using OAuth credentials (app ID, secret, and code) for
     * authorization.
     *
     * @param appId  the application ID for OAuth authentication
     * @param secret the application secret for OAuth authentication
     * @param code   the authorization code obtained from the OAuth flow
     */
    public DeezerClient(int appId, String secret, String code) {
        this(AccessTokenSuppliers.accessTokenSupplierFactory(appId, secret, code));
    }

    private DeezerClient(Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory) {
        this(null, null, accessTokenSupplierFactory);
    }

    @Builder
    private DeezerClient(
            @Nullable Consumer<BaseUrls.BaseUrlsBuilder> baseUrl,
            @Nullable Consumer<DeezerFeignBuilder> config,
            Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory
    ) {
        this(
                Customizer.customize(new DeezerFeignBuilder(), config).build(),
                Customizer.customize(BaseUrls.builder(), baseUrl).build(),
                new AccessTokenSupplier()
        );
        var oauth = requestFactoryProvider.oauth();
        accessTokenSupplier.setDelegate(() -> accessTokenSupplierFactory.apply(oauth));
    }

    private DeezerClient(
            AsyncFeign.AsyncBuilder<Object> feign,
            BaseUrls baseUrls,
            AccessTokenSupplier accessTokenSupplier
    ) {
        this(new RequestFactoryProvider(feign, baseUrls, accessTokenSupplier), accessTokenSupplier);
    }

    //endregion

    //region authorization

    /**
     * Authorizes the client using a raw access token string.
     *
     * @param accessToken the raw access token string to be used for authorization
     */
    public void authorization(String accessToken) {
        authorization(new AccessToken(accessToken));
    }

    /**
     * Authorizes the client using an {@link AccessToken} object.
     *
     * @param accessToken the {@link AccessToken} object to be used for authorization
     */
    public void authorization(AccessToken accessToken) {
        authorization(AccessTokenSuppliers.accessTokenSupplier(accessToken));
    }

    /**
     * Authorizes the client using OAuth credentials (app ID, secret, and code).
     *
     * @param appId  the application ID for OAuth authentication
     * @param secret the application secret for OAuth authentication
     * @param code   the authorization code obtained from the OAuth flow
     */
    public void authorization(int appId, String secret, String code) {
        authorization(AccessTokenSuppliers.accessTokenSupplier(oauth(), appId, secret, code));
    }

    private void authorization(Supplier<CompletableFuture<AccessToken>> delegate) {
        this.accessTokenSupplier.setDelegate(delegate);
    }

    //endregion

    /**
     * Removes the authorization from the client, effectively making it unauthorized.
     */
    public void removeAuthorization() {
        authorization(AccessTokenSuppliers.EMPTY_ACCESS_TOKEN_SUPPLIER);
    }

    /**
     * A builder for creating instances of {@link DeezerClient} with custom configurations.
     *
     * <p>
     * It allows setting the authorization method, base URLs, and Feign configurations. The builder provides a fluent
     * API for configuring the client and supports dynamic authorization by allowing the access token supplier to be set
     * at runtime.
     */
    @SuppressWarnings("NullAway.Init")
    public static class DeezerClientBuilder {
        private Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory =
                AccessTokenSuppliers.EMPTY_ACCESS_TOKEN_SUPPLIER_FACTORY;

        /**
         * Sets the authorization method using a raw access token string.
         *
         * @param accessToken the raw access token string to be used for authorization
         * @return the builder instance for chaining
         */
        public DeezerClientBuilder authorization(String accessToken) {
            return authorization(new AccessToken(accessToken));
        }

        /**
         * Sets the authorization method using an {@link AccessToken} object.
         *
         * @param accessToken the {@link AccessToken} object to be used for authorization
         * @return the builder instance for chaining
         */
        public DeezerClientBuilder authorization(AccessToken accessToken) {
            return accessTokenSupplierFactory(AccessTokenSuppliers.accessTokenSupplierFactory(accessToken));
        }

        /**
         * Sets the authorization method using OAuth credentials (app ID, secret, and code).
         *
         * @param appId  the application ID for OAuth authentication
         * @param secret the application secret for OAuth authentication
         * @param code   the authorization code obtained from the OAuth flow
         * @return the builder instance for chaining
         */
        public DeezerClientBuilder authorization(int appId, String secret, String code) {
            return accessTokenSupplierFactory(AccessTokenSuppliers.accessTokenSupplierFactory(appId, secret, code));
        }

        private DeezerClientBuilder accessTokenSupplierFactory(
                Function<OAuthRequestFactory, CompletableFuture<AccessToken>> delegate
        ) {
            this.accessTokenSupplierFactory = delegate;
            return this;
        }
    }
}
