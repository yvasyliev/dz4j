package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.exception.DeezerException;
import io.github.yvasyliev.dz4j.model.OEmbed;
import io.github.yvasyliev.dz4j.service.OEmbedService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

/**
 * A request to retrieve oEmbed information for a given Deezer URL.
 */
@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class OEmbedDeezerRequest extends AbstractDeezerRequest<OEmbed> {
    private final String url;
    private final OEmbedService oEmbedService;
    private @Nullable Boolean autoplay;
    private @Nullable Integer maxWidth;
    private @Nullable Integer maxHeight;
    private @Nullable Boolean radius;
    private @Nullable Boolean tracklist;

    @Override
    protected CompletableFuture<OEmbed> doExecuteAsync() {
        try {
            return oEmbedService.getOEmbed(new URL(url), autoplay, maxWidth, maxHeight, radius, tracklist);
        } catch (MalformedURLException e) {
            return CompletableFuture.failedFuture(new DeezerException("Invalid URL", e));
        }
    }
}
