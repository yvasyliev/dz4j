package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.exception.DeezerException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * An abstract implementation of the {@link DeezerRequest} interface that provides a default implementation for the
 * {@link #execute()} and {@link #executeAsync()} methods.
 *
 * <p>
 * Subclasses are required to implement the {@link #doExecuteAsync()} method, which performs the actual execution of the
 * request asynchronously.
 *
 * @param <T> the type of the response expected from the Deezer API
 */
public abstract class AbstractDeezerRequest<T> implements DeezerRequest<T> {
    /**
     * Executes the request synchronously by calling the {@link #executeAsync()} method and waiting for its completion.
     *
     * @return {@inheritDoc}
     * @throws DeezerException {@inheritDoc}
     */
    @Override
    public T execute() throws DeezerException {
        try {
            return executeAsync().join();
        } catch (CompletionException e) {
            throw translate(e);
        }
    }

    /**
     * Executes the request asynchronously by calling the {@link #doExecuteAsync()} method and handling any exceptions
     * that may occur during its execution.
     *
     * @return {@inheritDoc}
     */
    @Override
    public CompletableFuture<T> executeAsync() {
        return doExecuteAsync().exceptionally(t -> {
            throw translate(t);
        });
    }

    protected abstract CompletableFuture<T> doExecuteAsync();

    private DeezerException translate(Throwable t) {
        if (t instanceof DeezerException e) {
            return e;
        }

        if (t.getCause() instanceof DeezerException e) {
            return e;
        }

        return new DeezerException(t);
    }
}
