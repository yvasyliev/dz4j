package io.github.yvasyliev.deezer;

import io.github.yvasyliev.deezer.authorization.AccessTokenProvider;
import io.github.yvasyliev.deezer.authorization.AccessTokenSupplier;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.util.AuthHelper;
import io.github.yvasyliev.deezer.util.DeezerDefaults;
import io.github.yvasyliev.deezer.util.RequestFactoryProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DeezerClient {
    @Delegate private final RequestFactoryProvider requestFactoryProvider;
    private final AccessTokenSupplier accessTokenSupplier;

    public DeezerClient() {
        this(builder());
    }

    public DeezerClient(String accessToken) {
        this(builder().authorization(accessToken));
    }

    public DeezerClient(AccessToken accessToken) {
        this(builder().authorization(accessToken));
    }

    public DeezerClient(int appId, String secret, String code) {
        this(builder().authorization(appId, secret, code));
    }

    private DeezerClient(DeezerClientBuilder builder) {
        this(builder.build());
    }

    private DeezerClient(DeezerClient deezerClient) {
        this(deezerClient.requestFactoryProvider, deezerClient.accessTokenSupplier);
    }

    public static DeezerClientBuilder builder() {
        return new DeezerClientBuilder();
    }

    public void authorization(String accessToken) {
        authorization(AuthHelper.createAccessToken(accessToken));
    }

    public void authorization(AccessToken accessToken) {
        var accessTokenFuture = AuthHelper.createAccessTokenFuture(accessToken);

        authorization(() -> accessTokenFuture);
    }

    public void authorization(int appId, String secret, String code) {
        authorization(() -> oauth().getAccessToken(appId, secret, code).executeAsync());
    }

    private void authorization(AccessTokenProvider accessTokenProvider) {
        accessTokenSupplier.setAccessTokenProvider(accessTokenProvider);
    }

    public void removeAuthorization() {
        authorization(() -> DeezerDefaults.EMPTY_ACCESS_TOKEN_FUTURE);
    }
}
