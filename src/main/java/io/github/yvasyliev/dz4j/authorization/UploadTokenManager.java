package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.factory.InfosRequestFactory;
import io.github.yvasyliev.dz4j.model.Infos;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Manages the upload token required for uploading files to Deezer.
 */
@RequiredArgsConstructor
public class UploadTokenManager extends AbstractTokenManager<Infos> {
    private final InfosRequestFactory infos;

    /**
     * Retrieves the upload token, refreshing it if necessary.
     *
     * @return a {@link CompletableFuture} that will be completed with the upload token information
     */
    public CompletableFuture<Infos> getUploadToken() {
        return getToken(() -> infos.getInfos().executeAsync());
    }

    @Override
    protected boolean isInvalid(@Nullable CompletableFuture<Infos> tokenFuture) {
        if (super.isInvalid(tokenFuture)) {
            return true;
        }

        if (!Objects.requireNonNull(tokenFuture).isDone()) {
            return false;
        }

        return tokenFuture.isCompletedExceptionally() || tokenFuture.join().isUploadTokenExpired();
    }
}
