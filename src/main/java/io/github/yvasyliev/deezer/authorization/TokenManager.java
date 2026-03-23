package io.github.yvasyliev.deezer.authorization;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A token manager. Refreshes token when necessary. The class is thread-safe.
 *
 * @param <T> token type
 */
@RequiredArgsConstructor
public class TokenManager<T> {
    private final Predicate<CompletableFuture<T>> tokenValidator;
    private final Supplier<CompletableFuture<T>> tokenSupplier;
    private final Function<T, @Nullable String> tokenMapper;

    @Nullable
    private volatile CompletableFuture<T> token;

    /**
     * Returns a valid token. If the current token is invalid, it will be refreshed using the token supplier.
     *
     * @return a valid token
     */
    public CompletableFuture<@Nullable String> getToken() {
        var localToken = token;

        if (isInvalid(localToken)) {
            synchronized (this) {
                localToken = token;

                if (isInvalid(localToken)) {
                    localToken = tokenSupplier.get();
                    token = localToken;
                }
            }
        }

        return localToken.thenApply(tokenMapper);
    }

    private boolean isInvalid(@Nullable CompletableFuture<T> tokenFuture) {
        return tokenFuture == null || !tokenValidator.test(tokenFuture);
    }
}
