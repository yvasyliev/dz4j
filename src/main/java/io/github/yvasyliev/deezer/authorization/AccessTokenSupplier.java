package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.model.AccessToken;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * A supplier of access tokens. The actual supplier can be changed at runtime.
 */
@Setter
public class AccessTokenSupplier implements Supplier<CompletableFuture<AccessToken>> {
    @Delegate
    private volatile Supplier<CompletableFuture<AccessToken>> delegate;
}
