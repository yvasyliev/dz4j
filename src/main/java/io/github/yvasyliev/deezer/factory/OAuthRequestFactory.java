package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.service.OAuthService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuthRequestFactory {
    private final OAuthService oAuthService;

    public DeezerRequest<AccessToken> getAccessToken(int appId, String secret, String code) {
        return new SimpleDeezerRequest<>(() -> oAuthService.getAccessTokenAsync(appId, secret, code));
    }
}
