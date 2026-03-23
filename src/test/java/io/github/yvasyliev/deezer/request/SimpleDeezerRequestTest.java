package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SimpleDeezerRequestTest {
    @Test
    void shouldExecuteMethod() {
        var asyncMethod = Mockito.<Supplier<CompletableFuture<String>>>mock();
        var expected = "result";

        when(asyncMethod.get()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new SimpleDeezerRequest<>(asyncMethod).execute();

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecuteMethodWithToken() {
        var accessTokenManager = Mockito.<TokenManager<AccessToken>>mock();
        var asyncMethod = Mockito.<Function<String, CompletableFuture<String>>>mock();
        var accessToken = "access_token";
        var expected = "result";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(asyncMethod.apply(accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new SimpleDeezerRequest<>(accessTokenManager, asyncMethod).execute();

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecuteMethodWithTwoTokens() {
        var accessTokenManager = Mockito.<TokenManager<AccessToken>>mock();
        var uploadTokenManager = Mockito.<TokenManager<Infos>>mock();
        var asyncMethod = Mockito.<BiFunction<String, String, CompletableFuture<String>>>mock();
        var accessToken = "access_token";
        var uploadToken = "app_token";
        var expected = "result";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(uploadTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(uploadToken));
        when(asyncMethod.apply(accessToken, uploadToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new SimpleDeezerRequest<>(accessTokenManager, uploadTokenManager, asyncMethod).execute();

        assertEquals(expected, actual);
    }
}
