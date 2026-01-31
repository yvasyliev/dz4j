package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.exception.DeezerException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public abstract class AbstractDeezerRequest<T> implements DeezerRequest<T> {
    @Override
    public T execute() throws DeezerException {
        try {
            return executeAsync().join();
        } catch (CompletionException e) {
            throw translate(e);
        }
    }

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
