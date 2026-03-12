package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.model.Page;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SimplePagingDeezerRequestTest {
    @Test
    void shouldReturnPage() {
        var asyncMethod = Mockito.<BiFunction<Integer, Integer, CompletableFuture<Page<String>>>>mock();
        var index = 5;
        var limit = 10;
        var expected = Page.<String>builder().data(List.of("result")).build();

        when(asyncMethod.apply(index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new SimplePagingDeezerRequest<>(asyncMethod)
                .index(index)
                .limit(limit)
                .execute();

        assertEquals(expected, actual);
    }
}
