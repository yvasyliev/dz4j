package io.github.yvasyliev.deezer.v2.methods;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface DzMethod<T> {
    CompletableFuture<T> executeAsync();

    default T execute() {
        return executeAsync().join();
    }
}
