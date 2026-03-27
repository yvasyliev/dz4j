package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.model.Order;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Query;
import io.github.yvasyliev.dz4j.model.SimpleQuery;
import io.github.yvasyliev.dz4j.util.QuinaryFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SearchDeezerRequestTest {
    @Test
    void shouldReturnPage() {
        var query = new SimpleQuery("test");
        var asyncMethod = Mockito
                .<QuinaryFunction<Query, Boolean, Order, Integer, Integer, CompletableFuture<Page<String>>>>mock();
        var strict = true;
        var order = Order.TRACK_DESC;
        var index = 5;
        var limit = 10;
        var expected = Page.<String>builder().data(List.of("result")).build();

        when(asyncMethod.apply(query, strict, order, index, limit))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new SearchDeezerRequest<>(query, asyncMethod)
                .strict(strict)
                .order(order)
                .limit(limit)
                .index(index)
                .execute();

        assertEquals(expected, actual);
    }
}
