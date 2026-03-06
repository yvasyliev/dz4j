package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface AccessTokenProvider {
    CompletableFuture<AccessToken> getAccessToken() throws DeezerException;
}
