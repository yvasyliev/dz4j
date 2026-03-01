package io.github.yvasyliev.deezer.authorization;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class TokenManager<T> {
    private final Predicate<CompletableFuture<T>> tokenValidator;
    private final Supplier<CompletableFuture<T>> tokenSupplier;
    private final Function<T, String> tokenMapper;
    private volatile CompletableFuture<T> token;

    public CompletableFuture<String> getToken() {
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

    private boolean isInvalid(CompletableFuture<T> token) {
        return token == null || !tokenValidator.test(token);
    }
}
