package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface AccessTokenProvider {
    CompletableFuture<AccessToken> getAccessToken() throws DeezerException;

    static AccessTokenProvider of(String accessToken) {
        return of(new AccessToken(accessToken, Instant.MAX));
    }

    static AccessTokenProvider of(AccessToken accessToken) {
        return () -> CompletableFuture.completedFuture(accessToken);
    }
}
