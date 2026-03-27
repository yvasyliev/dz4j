package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.model.AccessToken;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * A supplier of access tokens. The actual supplier can be changed at runtime.
 */
@Setter
public class AccessTokenSupplier implements Supplier<CompletableFuture<AccessToken>> {
    @Delegate
    @Nullable
    private volatile Supplier<CompletableFuture<AccessToken>> delegate;
}
