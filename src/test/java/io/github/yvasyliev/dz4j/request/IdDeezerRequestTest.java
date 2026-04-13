package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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
        var accessToken = new AccessToken("test-token");
        var authorizationManager = mock(AuthorizationManager.class);
        var asyncMethod = Mockito.<BiFunction<Long, AccessToken, CompletableFuture<String>>>mock();
        var expected = "result";

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(asyncMethod.apply(id, accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new IdDeezerRequest<>(id, authorizationManager, asyncMethod).execute();

        assertEquals(expected, actual);
    }
}
