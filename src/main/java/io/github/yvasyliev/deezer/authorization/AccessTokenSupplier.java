package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.model.AccessToken;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Setter
public class AccessTokenSupplier implements Supplier<CompletableFuture<AccessToken>> {
    @Delegate
    private volatile Supplier<CompletableFuture<AccessToken>> delegate;
}
