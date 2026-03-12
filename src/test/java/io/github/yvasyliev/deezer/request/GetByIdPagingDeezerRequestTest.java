package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.util.QuadFunction;
import io.github.yvasyliev.deezer.util.TriFunction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        var accessTokenManager = Mockito.<TokenManager<AccessToken>>mock();
        var asyncMethod = Mockito.<QuadFunction<Long, String, Integer, Integer, CompletableFuture<String>>>mock();
        var accessToken = "access_token";
        var index = 1;
        var limit = 10;
        var expected = "result";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(asyncMethod.apply(id, accessToken, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new GetByIdPagingDeezerRequest<>(id, accessTokenManager, asyncMethod)
                .index(index)
                .limit(limit)
                .execute();

        assertEquals(expected, actual);
    }
}
