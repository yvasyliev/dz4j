package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.exception.DeezerException;

import java.util.concurrent.CompletableFuture;

/**
 * Represents a request to the Deezer API that can be executed synchronously or asynchronously.
 *
 * <p>
 * It's recommended to extend {@link AbstractDeezerRequest} instead of implementing this interface directly for the
 * streamlined handling of exceptions and asynchronous execution.
 *
 * @param <T> the type of the response expected from the Deezer API
 */
public interface DeezerRequest<T> {
    /**
     * Executes the request synchronously and returns the response.
     *
     * @return the response from the Deezer API
     * @throws DeezerException if an error occurs during the execution of the request
     */
    T execute() throws DeezerException;

    /**
     * Executes the request asynchronously and returns a {@link CompletableFuture} that will be completed with the
     * response.
     *
     * @return a {@link CompletableFuture} that will be completed with the response from the Deezer API
     */
    CompletableFuture<T> executeAsync();
}
