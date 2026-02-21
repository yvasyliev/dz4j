package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.factory.OAuthRequestFactory;
import io.github.yvasyliev.deezer.model.AccessToken;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationCodeFlow implements AccessTokenProvider {
    private final OAuthRequestFactory oAuthRequestFactory;
    private final int appId;
    private final String secret;
    private final String code;
    private AccessTokenProvider delegate;

    @Override
    public AccessToken getAccessToken() throws DeezerException {
        if (delegate == null) {
            delegate = AccessTokenProvider.of(oAuthRequestFactory.getAccessToken(appId, secret, code).execute());
        }

        return delegate.getAccessToken();
    }
}
