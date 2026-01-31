package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.exception.DeezerException;

import java.util.concurrent.CompletableFuture;

public interface DeezerRequest<T> {
    T execute() throws DeezerException;

    CompletableFuture<T> executeAsync();
}
