package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.util.QuadFunction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

//TODO: remove?
@RequiredArgsConstructor
@Setter(onMethod_ = @Override)
@Accessors(fluent = true)
public class GetByUserIdPagingDeezerRequest<T> extends AbstractDeezerRequest<T> implements PagingDeezerRequest<T> {
    private final String userId;
    private final TokenManager<AccessToken> accessTokenManager;
    private final QuadFunction<String, String, Integer, Integer, CompletableFuture<T>> asyncMethod;
    private Integer index;
    private Integer limit;

    @Override
    protected CompletableFuture<T> doExecuteAsync() {
        return accessTokenManager.getToken().thenCompose(accessToken -> asyncMethod.apply(
                userId,
                accessToken,
                index,
                limit
        ));
    }
}
