package io.github.yvasyliev.dz4j.authorization;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import org.jspecify.annotations.Nullable;

/**
 * An abstract token manager that provides a thread-safe mechanism for retrieving and caching tokens asynchronously.
 *
 * @param <T> the type of token managed by this class
 */
public abstract class AbstractTokenManager<T> {
    private volatile @Nullable CompletableFuture<T> token;

    protected boolean isInvalid(@Nullable CompletableFuture<T> tokenFuture) {
        return tokenFuture == null;
    }

    protected CompletableFuture<T> getToken(Supplier<CompletableFuture<T>> tokenSupplier) {
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

        return Objects.requireNonNull(localToken);
    }
}
