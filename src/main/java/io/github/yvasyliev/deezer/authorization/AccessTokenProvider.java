package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.time.Instant;

@FunctionalInterface
public interface AccessTokenProvider {
    AccessToken getAccessToken() throws DeezerException;

    static AccessTokenProvider of(String accessToken) {
        return of(new AccessToken(accessToken, Instant.MAX));
    }

    static AccessTokenProvider of(AccessToken accessToken) {
        return () -> accessToken;
    }
}
