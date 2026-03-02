package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.model.Page;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

@RequiredArgsConstructor
@Setter(onMethod_ = @Override)
@Accessors(fluent = true)
public class SimplePagingDeezerRequest<T> extends AbstractDeezerRequest<Page<T>>
        implements PagingDeezerRequest<Page<T>> {
    private final BiFunction<Integer, Integer, CompletableFuture<Page<T>>> asyncMethod;
    private Integer index;
    private Integer limit;

    @Override
    protected CompletableFuture<Page<T>> doExecuteAsync() {
        return asyncMethod.apply(index, limit);
    }
}
