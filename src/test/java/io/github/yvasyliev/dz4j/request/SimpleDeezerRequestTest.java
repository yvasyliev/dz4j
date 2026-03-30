package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.authorization.UploadTokenManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Infos;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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
        var authorizationManager = mock(AuthorizationManager.class);
        var asyncMethod = Mockito.<Function<AccessToken, CompletableFuture<String>>>mock();
        var accessToken = new AccessToken("test-token");
        var expected = "result";

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(asyncMethod.apply(accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new SimpleDeezerRequest<>(authorizationManager, asyncMethod).execute();

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecuteMethodWithTwoTokens() {
        var authorizationManager = mock(AuthorizationManager.class);
        var uploadTokenManager = mock(UploadTokenManager.class);
        var asyncMethod = Mockito.<BiFunction<AccessToken, Infos, CompletableFuture<String>>>mock();
        var accessToken = new AccessToken("test-token");
        var infos = Infos.builder().uploadToken("token").build();
        var expected = "result";

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(uploadTokenManager.getUploadToken()).thenReturn(CompletableFuture.completedFuture(infos));
        when(asyncMethod.apply(accessToken, infos)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new SimpleDeezerRequest<>(authorizationManager, uploadTokenManager, asyncMethod).execute();

        assertEquals(expected, actual);
    }
}
