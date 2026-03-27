package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.authorization.TokenManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A request to the Deezer API that retrieves a resource by its ID.
 *
 * @param <T> the type of the response expected from the Deezer API
 */
@RequiredArgsConstructor
public class IdDeezerRequest<T> extends AbstractDeezerRequest<T> {
    private final long id;
    private final Function<Long, CompletableFuture<T>> asyncMethod;

    /**
     * Constructs a new {@link IdDeezerRequest} with the specified ID, access token manager, and asynchronous method to
     * retrieve the resource.
     *
     * @param id           a resource ID
     * @param tokenManager an access token manager to retrieve the token for the request
     * @param asyncMethod  a function that takes the resource ID and token as parameters and returns a
     *                     {@link CompletableFuture} that will be completed with the response from the Deezer API
     */
    public IdDeezerRequest(
            long id,
            TokenManager<AccessToken> tokenManager,
            BiFunction<Long, @Nullable String, CompletableFuture<T>> asyncMethod
    ) {
        this(id, objId -> tokenManager.getToken().thenCompose(token -> asyncMethod.apply(objId, token)));
    }

    @Override
    protected CompletableFuture<T> doExecuteAsync() {
        return asyncMethod.apply(id);
    }
}
