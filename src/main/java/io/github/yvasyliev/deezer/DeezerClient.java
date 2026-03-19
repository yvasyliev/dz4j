package io.github.yvasyliev.deezer;

import io.github.yvasyliev.deezer.authorization.AccessTokenSupplier;
import io.github.yvasyliev.deezer.factory.OAuthRequestFactory;
import io.github.yvasyliev.deezer.feign.DeezerFeignBuilder;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.util.AccessTokenSuppliers;
import io.github.yvasyliev.deezer.util.BaseUrls;
import io.github.yvasyliev.deezer.util.Customizer;
import io.github.yvasyliev.deezer.util.RequestFactoryProvider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeezerClient {
    @Delegate
    private final RequestFactoryProvider requestFactoryProvider;
    private final AccessTokenSupplier accessTokenSupplier;

    //region constructors

    public DeezerClient() {
        this(new AccessToken());
    }

    public DeezerClient(String accessToken) {
        this(new AccessToken(accessToken));
    }

    public DeezerClient(AccessToken accessToken) {
        this(AccessTokenSuppliers.accessTokenSupplierFactory(accessToken));
    }

    public DeezerClient(int appId, String secret, String code) {
        this(AccessTokenSuppliers.accessTokenSupplierFactory(appId, secret, code));
    }

    private DeezerClient(Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory) {
        this(null, null, accessTokenSupplierFactory);
    }

    @Builder
    private DeezerClient(
            Consumer<BaseUrls.BaseUrlsBuilder> baseUrl,
            Consumer<DeezerFeignBuilder> config,
            Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory
    ) {
        this(baseUrl, config, new AccessTokenSupplier());
        var oauth = requestFactoryProvider.oauth();
        accessTokenSupplier.setDelegate(() -> accessTokenSupplierFactory.apply(oauth));
    }

    private DeezerClient(
            Consumer<BaseUrls.BaseUrlsBuilder> baseUrl,
            Consumer<DeezerFeignBuilder> config,
            AccessTokenSupplier accessTokenSupplier
    ) {
        this(
                new RequestFactoryProvider(
                        Customizer.customize(new DeezerFeignBuilder(), config).build(),
                        Customizer.customize(BaseUrls.builder(), baseUrl).build(),
                        accessTokenSupplier
                ),
                accessTokenSupplier
        );
    }

    //endregion

    //region authorization

    public void authorization(String accessToken) {
        authorization(new AccessToken(accessToken));
    }

    public void authorization(AccessToken accessToken) {
        authorization(AccessTokenSuppliers.accessTokenSupplier(accessToken));
    }

    public void authorization(int appId, String secret, String code) {
        authorization(AccessTokenSuppliers.accessTokenSupplier(oauth(), appId, secret, code));
    }

    private void authorization(Supplier<CompletableFuture<AccessToken>> accessTokenSupplier) {
        this.accessTokenSupplier.setDelegate(accessTokenSupplier);
    }

    //endregion

    public void removeAuthorization() {
        authorization(AccessTokenSuppliers.EMPTY_ACCESS_TOKEN_SUPPLIER);
    }

    public static class DeezerClientBuilder {
        private Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory =
                AccessTokenSuppliers.EMPTY_ACCESS_TOKEN_SUPPLIER_FACTORY;

        public DeezerClientBuilder authorization(String accessToken) {
            return authorization(new AccessToken(accessToken));
        }

        public DeezerClientBuilder authorization(AccessToken accessToken) {
            return accessTokenSupplierFactory(AccessTokenSuppliers.accessTokenSupplierFactory(accessToken));
        }

        public DeezerClientBuilder authorization(int appId, String secret, String code) {
            return accessTokenSupplierFactory(AccessTokenSuppliers.accessTokenSupplierFactory(appId, secret, code));
        }

        private DeezerClientBuilder accessTokenSupplierFactory(
                Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenSupplierFactory
        ) {
            this.accessTokenSupplierFactory = accessTokenSupplierFactory;
            return this;
        }
    }
}
