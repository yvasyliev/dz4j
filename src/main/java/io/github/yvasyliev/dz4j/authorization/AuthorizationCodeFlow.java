package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the authorization code flow for obtaining an access token.
 */
@RequiredArgsConstructor
public class AuthorizationCodeFlow extends AbstractTokenManager<AccessToken> implements Authorization {
    private final int appId;
    private final String secret;
    private final String code;

    /**
     * Obtains an access token using the authorization code flow. If the token has already been obtained, it returns the
     * cached token.
     *
     * @param auth {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public CompletableFuture<AccessToken> getAccessToken(OAuthRequestFactory auth) {
        return getToken(() -> auth.getAccessToken(appId, secret, code).executeAsync());
    }
}
