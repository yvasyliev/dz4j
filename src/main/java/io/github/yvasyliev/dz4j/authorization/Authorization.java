package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;

import java.util.concurrent.CompletableFuture;

/**
 * Functional interface for obtaining an access token for authorization.
 */
@FunctionalInterface
public interface Authorization {
    /**
     * Retrieves the access token for authorization.
     *
     * @param auth the {@link OAuthRequestFactory} used to obtain the token
     * @return a {@link CompletableFuture} that will complete with the access token as a string
     */
    CompletableFuture<AccessToken> getAccessToken(OAuthRequestFactory auth);

    /**
     * Creates an {@link Authorization} instance that always returns the provided access token.
     *
     * @param accessToken the access token to be returned by the {@link Authorization} instance
     * @return an {@link Authorization} instance that always returns the provided access token
     */
    static Authorization of(AccessToken accessToken) {
        var token = CompletableFuture.completedFuture(accessToken);

        return auth -> token;
    }
}
