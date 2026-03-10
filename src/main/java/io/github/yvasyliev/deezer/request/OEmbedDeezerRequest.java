package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.model.OEmbed;
import io.github.yvasyliev.deezer.service.OEmbedService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class OEmbedDeezerRequest extends AbstractDeezerRequest<OEmbed> {
    private final String url;
    private final OEmbedService oEmbedService;
    private Boolean autoplay;
    private Integer maxWidth;
    private Integer maxHeight;
    private Boolean radius;
    private Boolean tracklist;

    @Override
    protected CompletableFuture<OEmbed> doExecuteAsync() {
        try {
            return oEmbedService.getOEmbed(new URL(url), autoplay, maxWidth, maxHeight, radius, tracklist);
        } catch (MalformedURLException e) {
            return CompletableFuture.failedFuture(new DeezerException("Invalid URL", e));
        }
    }
}
