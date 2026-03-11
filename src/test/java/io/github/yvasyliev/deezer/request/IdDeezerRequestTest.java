package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class IdDeezerRequestTest {
    @Test
    void shouldExecuteMethod() {
        var id = 123L;
        var asyncMethod = Mockito.<Function<Long, CompletableFuture<String>>>mock();
        var expected = "result";

        when(asyncMethod.apply(id)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new IdDeezerRequest<>(id, asyncMethod).execute();

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecuteMethodWithToken() {
        var id = 123L;
        var token = "token";
        var tokenManager = Mockito.<TokenManager<Object>>mock();
        var asyncMethod = Mockito.<BiFunction<Long, String, CompletableFuture<String>>>mock();
        var expected = "result";

        when(tokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(asyncMethod.apply(id, token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new IdDeezerRequest<>(id, tokenManager, asyncMethod).execute();

        assertEquals(expected, actual);
    }
}
