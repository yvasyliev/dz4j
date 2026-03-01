package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class SimpleDeezerRequest<R> extends AbstractDeezerRequest<R> {
    private final Supplier<CompletableFuture<R>> asyncMethod;

    public <T> SimpleDeezerRequest(TokenManager<T> tokenManager, Function<String, CompletableFuture<R>> asyncMethod) {
        this(() -> tokenManager.getToken().thenCompose(asyncMethod));
    }

    public <T, U> SimpleDeezerRequest(
            TokenManager<T> tokenManager1,
            TokenManager<U> tokenManager2,
            BiFunction<String, String, CompletableFuture<R>> asyncMethod
    ) {
        this(() -> tokenManager1.getToken()
                .thenCombine(tokenManager2.getToken(), asyncMethod)
                .thenCompose(Function.identity())
        );
    }

    @Override
    protected CompletableFuture<R> doExecuteAsync() {
        return asyncMethod.get();
    }
}
