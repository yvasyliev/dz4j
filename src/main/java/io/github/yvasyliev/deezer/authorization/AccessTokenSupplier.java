package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.model.AccessToken;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@AllArgsConstructor
@Setter
public class AccessTokenSupplier implements Supplier<CompletableFuture<AccessToken>> {
    private volatile AccessTokenProvider accessTokenProvider;

    @Override
    public CompletableFuture<AccessToken> get() {
        return accessTokenProvider.getAccessToken();
    }
}
