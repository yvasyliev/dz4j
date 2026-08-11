package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.exception.DeezerException;

import java.util.concurrent.CompletableFuture;

/**
 * Represents a request to the Deezer API that can be executed synchronously or asynchronously.
 *
 * @param <T> the type of the response expected from the Deezer API
 * @implNote Implementers are encouraged to extend {@link AbstractDeezerRequest} rather than {@code implement}
 *         this interface directly; the abstract base class provides default exception handling and a standard
 *         asynchronous implementation.
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
