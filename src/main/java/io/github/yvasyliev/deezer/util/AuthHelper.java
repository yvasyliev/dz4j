package io.github.yvasyliev.deezer.util;

import io.github.yvasyliev.deezer.model.AccessToken;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@UtilityClass
public class AuthHelper {
    public AccessToken createAccessToken(String token) {
        return new AccessToken(token, Instant.MAX);
    }

    public CompletableFuture<AccessToken> createAccessTokenFuture(AccessToken accessToken) {
        return CompletableFuture.completedFuture(accessToken);
    }
}
