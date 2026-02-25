package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.AuthorizationContext;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.util.QuadFunction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter(onMethod_ = @Override)
@Accessors(fluent = true)
public class GetByUserIdPagingDeezerRequest<T> extends AbstractDeezerRequest<T> implements PagingDeezerRequest<T> {
    private final String userId;
    private final AuthorizationContext authorizationContext;
    private final QuadFunction<String, AccessToken, Integer, Integer, CompletableFuture<T>> asyncMethod;
    private Integer index;
    private Integer limit;

    @Override
    protected CompletableFuture<T> doExecuteAsync() {
        return asyncMethod.apply(userId, authorizationContext.getAccessTokenProvider().getAccessToken(), index, limit);
    }
}
