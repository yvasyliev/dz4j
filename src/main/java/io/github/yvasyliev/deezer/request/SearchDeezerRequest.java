package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.model.Order;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Query;
import io.github.yvasyliev.deezer.util.QuinaryFunction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
    private Boolean strict;
    private Order order;

    @Setter(onMethod_ = @Override)
    private Integer index;

    @Setter(onMethod_ = @Override)
    private Integer limit;

    @Override
    protected CompletableFuture<Page<T>> doExecuteAsync() {
        return asyncMethod.apply(query, strict, order, index, limit);
    }
}
