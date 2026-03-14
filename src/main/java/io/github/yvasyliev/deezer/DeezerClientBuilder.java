package io.github.yvasyliev.deezer;

import feign.AsyncFeign;
import io.github.yvasyliev.deezer.authorization.AccessTokenSupplier;
import io.github.yvasyliev.deezer.factory.OAuthRequestFactory;
import io.github.yvasyliev.deezer.feign.DeezerContract;
import io.github.yvasyliev.deezer.feign.decoder.DeezerDecoder;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.util.AuthHelper;
import io.github.yvasyliev.deezer.util.DeezerDefaults;
import io.github.yvasyliev.deezer.util.FeignConfigurator;
import io.github.yvasyliev.deezer.util.RequestFactoryProvider;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import tools.jackson.databind.json.JsonMapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

@Setter
@Accessors(fluent = true, chain = true)
public class DeezerClientBuilder {
    private JsonMapper jsonMapper = DeezerDefaults.jsonMapper();
    private Consumer<DeezerDecoder.DeezerDecoderBuilder> decoder = DeezerDefaults.noopConsumer();
    private Consumer<DeezerContract.DeezerContractBuilder> contract = DeezerDefaults.noopConsumer();
    private Consumer<AsyncFeign.AsyncBuilder<Object>> feign = DeezerDefaults.noopConsumer();
    private String apiBaseUrl = DeezerDefaults.API_BASE_URL;
    private String oauthBaseUrl = DeezerDefaults.OAUTH_BASE_URL;
    private String uploadBaseUrl = DeezerDefaults.UPLOAD_BASE_URL;

    @Setter(AccessLevel.PRIVATE)
    private Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenProviderFactory = oauth ->
            DeezerDefaults.EMPTY_ACCESS_TOKEN_FUTURE;

    public DeezerClientBuilder authorization(String accessToken) {
        return authorization(AuthHelper.createAccessToken(accessToken));
    }

    public DeezerClientBuilder authorization(AccessToken accessToken) {
        var accessTokenFuture = AuthHelper.createAccessTokenFuture(accessToken);

        return accessTokenProviderFactory(oauth -> accessTokenFuture);
    }

    public DeezerClientBuilder authorization(int appId, String secret, String code) {
        return accessTokenProviderFactory(oauth -> oauth.getAccessToken(appId, secret, code).executeAsync());
    }

    public DeezerClient build() {
        var builder = FeignConfigurator.build(jsonMapper, decoder, contract, feign);
        var accessTokenSupplier = new AccessTokenSupplier();
        var requestFactoryProvider = new RequestFactoryProvider(
                builder,
                apiBaseUrl,
                oauthBaseUrl,
                uploadBaseUrl,
                accessTokenSupplier
        );
        var oauth = requestFactoryProvider.oauth();

        accessTokenSupplier.setAccessTokenProvider(() -> accessTokenProviderFactory.apply(oauth));

        return new DeezerClient(requestFactoryProvider, accessTokenSupplier);
    }
}
