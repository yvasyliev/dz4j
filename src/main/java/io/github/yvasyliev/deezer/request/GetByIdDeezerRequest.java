package io.github.yvasyliev.deezer.request;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetByIdDeezerRequest<T> extends AbstractDeezerRequest<T> {
    private final long id;
    private final Function<Long, CompletableFuture<T>> asyncMethod;

    @Override
    protected CompletableFuture<T> doExecuteAsync() {
        return asyncMethod.apply(id);
    }
}
