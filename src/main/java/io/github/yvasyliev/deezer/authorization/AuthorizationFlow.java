package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.time.Instant;

@FunctionalInterface
public interface AuthorizationFlow {
    String getAccessToken() throws DeezerException;

    static AuthorizationFlow of(String accessToken) {
        return of(new AccessToken(accessToken, Instant.MAX));
    }

    static AuthorizationFlow of(AccessToken accessToken) {
        return () -> {
            if (accessToken.isExpired()) {
                throw new DeezerException("Access token is expired");
            }

            return accessToken.token();
        };
    }
}
