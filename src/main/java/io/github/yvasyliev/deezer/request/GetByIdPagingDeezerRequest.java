package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.util.TriFunction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter(onMethod_ = @Override)
@Accessors(fluent = true)
public class GetByIdPagingDeezerRequest<T> extends AbstractDeezerRequest<T> implements PagingDeezerRequest<T> {
    private final long objectId;
    private final TriFunction<Long, Integer, Integer, CompletableFuture<T>> asyncMethod;
    private Integer index;
    private Integer limit;

    @Override
    protected CompletableFuture<T> doExecuteAsync() {
        return asyncMethod.apply(objectId, index, limit);
    }
}
