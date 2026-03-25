package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A simple request to the Deezer API that executes a provided asynchronous method.
 *
 * @param <T> the type of the response expected from the Deezer API
 */
@RequiredArgsConstructor
public class SimpleDeezerRequest<T> extends AbstractDeezerRequest<T> {
    private final Supplier<CompletableFuture<T>> asyncMethod;

    /**
     * Constructs a new {@link SimpleDeezerRequest} with the specified access token manager and asynchronous method to
     * execute.
     *
     * @param accessTokenManager an access token manager to retrieve the token for the request
     * @param asyncMethod        a function that takes the access token as a parameter and returns a
     *                           {@link CompletableFuture} that will be completed with the response from the Deezer API
     */
    public SimpleDeezerRequest(
            TokenManager<AccessToken> accessTokenManager,
            Function<String, CompletableFuture<T>> asyncMethod
    ) {
        this(() -> accessTokenManager.getToken().thenCompose(asyncMethod));
    }

    /**
     * Constructs a new {@link SimpleDeezerRequest} with the specified access token manager, upload token manager, and
     * asynchronous method to execute.
     *
     * @param accessTokenManager an access token manager to retrieve the access token for the request
     * @param uploadTokenManager an upload token manager to retrieve the upload token for the request
     * @param asyncMethod        a function that takes the access token and upload token as parameters and returns a
     *                           {@link CompletableFuture} that will be completed with the response from the Deezer API
     */
    public SimpleDeezerRequest(
            TokenManager<AccessToken> accessTokenManager,
            TokenManager<Infos> uploadTokenManager,
            BiFunction<@Nullable String, @Nullable String, CompletableFuture<T>> asyncMethod
    ) {
        this(() -> {
            var accessTokenFuture = accessTokenManager.getToken();
            var uploadTokenFuture = uploadTokenManager.getToken();

            return CompletableFuture.allOf(accessTokenFuture, uploadTokenFuture)
                    .thenCompose(ignored -> asyncMethod.apply(accessTokenFuture.join(), uploadTokenFuture.join()));
        });
    }

    @Override
    protected CompletableFuture<T> doExecuteAsync() {
        return asyncMethod.get();
    }
}
