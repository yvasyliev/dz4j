package io.github.yvasyliev.deezer.request;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class SimpleDeezerRequest<T> extends AbstractDeezerRequest<T> {
    private final Supplier<CompletableFuture<T>> asyncMethod;

    @Override
    protected CompletableFuture<T> doExecuteAsync() {
        return asyncMethod.get();
    }
}
