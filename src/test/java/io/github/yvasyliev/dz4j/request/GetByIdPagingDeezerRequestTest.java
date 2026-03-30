package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.util.QuadFunction;
import io.github.yvasyliev.dz4j.util.TriFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetByIdPagingDeezerRequestTest {
    @Test
    void shouldExecuteMethod() {
        var id = 123L;
        var asyncMethod = Mockito.<TriFunction<Long, Integer, Integer, CompletableFuture<String>>>mock();
        var index = 1;
        var limit = 10;
        var expected = "result";

        when(asyncMethod.apply(id, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new GetByIdPagingDeezerRequest<>(id, asyncMethod)
                .index(index)
                .limit(limit)
                .execute();

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecuteMethodWithAccessToken() {
        var id = 123L;
        var authorizationManager = mock(AuthorizationManager.class);
        var asyncMethod = Mockito.<QuadFunction<Long, AccessToken, Integer, Integer, CompletableFuture<String>>>mock();
        var accessToken = new AccessToken("test-token");
        var index = 1;
        var limit = 10;
        var expected = "result";

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(asyncMethod.apply(id, accessToken, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new GetByIdPagingDeezerRequest<>(id, authorizationManager, asyncMethod)
                .index(index)
                .limit(limit)
                .execute();

        assertEquals(expected, actual);
    }
}
