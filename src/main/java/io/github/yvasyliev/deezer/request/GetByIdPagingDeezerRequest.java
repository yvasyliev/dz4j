package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.util.QuadFunction;
import io.github.yvasyliev.deezer.util.TriFunction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * A request to retrieve a paginated resource from the Deezer API by its ID.
 *
 * @param <T> the type of the ID used to identify the resource
 * @param <R> the type of the response expected from the Deezer API
 */
@RequiredArgsConstructor
@Setter(onMethod_ = @Override)
@Accessors(fluent = true)
public class GetByIdPagingDeezerRequest<T, R> extends AbstractDeezerRequest<R> implements PagingDeezerRequest<R> {
    private final T id;
    private final TriFunction<T, Integer, Integer, CompletableFuture<R>> asyncMethod;
    @Nullable private Integer index;
    @Nullable private Integer limit;

    /**
     * Constructs a new {@link GetByIdPagingDeezerRequest} with the specified ID, access token manager, and asynchronous
     * method to retrieve the resource.
     *
     * @param id                 a resource ID
     * @param accessTokenManager an access token manager to retrieve the access token for the request
     * @param asyncMethod        a function that takes the resource ID, access token, index, and limit as parameters and
     *                           returns a {@link CompletableFuture} that will be completed with the response from the
     *                           Deezer API
     */
    public GetByIdPagingDeezerRequest(
            T id,
            TokenManager<AccessToken> accessTokenManager,
            QuadFunction<T, String, Integer, Integer, CompletableFuture<R>> asyncMethod
    ) {
        this(
                id,
                (objId, idx, lmt) -> accessTokenManager.getToken()
                        .thenCompose(accessToken -> asyncMethod.apply(id, accessToken, idx, lmt))
        );
    }

    @Override
    protected CompletableFuture<R> doExecuteAsync() {
        return asyncMethod.apply(id, index, limit);
    }
}
