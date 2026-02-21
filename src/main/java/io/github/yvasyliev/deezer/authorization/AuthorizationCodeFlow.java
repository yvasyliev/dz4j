package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.factory.OAuthRequestFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationCodeFlow implements AuthorizationFlow {
    private final OAuthRequestFactory oAuthRequestFactory;
    private final int appId;
    private final String secret;
    private final String code;
    private AuthorizationFlow delegate;

    @Override
    public String getAccessToken() throws DeezerException {
        if (delegate == null) {
            delegate = AuthorizationFlow.of(oAuthRequestFactory.getAccessToken(appId, secret, code).execute());
        }

        return delegate.getAccessToken();
    }
}
