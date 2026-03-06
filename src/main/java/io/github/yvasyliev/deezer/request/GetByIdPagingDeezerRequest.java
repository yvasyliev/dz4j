package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.util.QuadFunction;
import io.github.yvasyliev.deezer.util.TriFunction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter(onMethod_ = @Override)
@Accessors(fluent = true)
public class GetByIdPagingDeezerRequest<T, R> extends AbstractDeezerRequest<R> implements PagingDeezerRequest<R> {
    private final T id;
    private final TriFunction<T, Integer, Integer, CompletableFuture<R>> asyncMethod;
    private Integer index;
    private Integer limit;

    public GetByIdPagingDeezerRequest(
            T id,
            TokenManager<AccessToken> accessTokenManager,
            QuadFunction<T, String, Integer, Integer, CompletableFuture<R>> asyncMethod
    ) {
        this(
                id,
                (objId, index, limit) -> accessTokenManager.getToken()
                        .thenCompose(accessToken -> asyncMethod.apply(id, accessToken, index, limit))
        );
    }

    @Override
    protected CompletableFuture<R> doExecuteAsync() {
        return asyncMethod.apply(id, index, limit);
    }
}
