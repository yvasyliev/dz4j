package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public class IdDeezerRequest<R> extends AbstractDeezerRequest<R> {
    private final long id;
    private final Function<Long, CompletableFuture<R>> asyncMethod;

    public <T> IdDeezerRequest(
            long id,
            TokenManager<T> tokenManager,
            BiFunction<Long, String, CompletableFuture<R>> asyncMethod
    ) {
        this(id, objId -> tokenManager.getToken().thenCompose(token -> asyncMethod.apply(objId, token)));
    }

    @Override
    protected CompletableFuture<R> doExecuteAsync() {
        return asyncMethod.apply(id);
    }
}
