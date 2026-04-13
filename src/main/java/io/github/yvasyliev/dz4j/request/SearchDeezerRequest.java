package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.model.Order;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Query;
import io.github.yvasyliev.dz4j.util.QuinaryFunction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * A request to search for resources on Deezer based on a query.
 *
 * @param <T> the type of the resources being searched for
 */
@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class SearchDeezerRequest<T> extends AbstractDeezerRequest<Page<T>> implements PagingDeezerRequest<Page<T>> {
    private final Query query;
    private final QuinaryFunction<Query, Boolean, Order, Integer, Integer, CompletableFuture<Page<T>>> asyncMethod;
    @Nullable private Boolean strict;
    @Nullable private Order order;

    @Setter(onMethod_ = @Override)
    @Nullable
    private Integer index;

    @Setter(onMethod_ = @Override)
    @Nullable
    private Integer limit;

    @Override
    protected CompletableFuture<Page<T>> doExecuteAsync() {
        return asyncMethod.apply(query, strict, order, index, limit);
    }
}
