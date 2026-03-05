package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.OEmbed;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.OEmbedDeezerRequest;
import io.github.yvasyliev.deezer.service.OEmbedService;
import lombok.RequiredArgsConstructor;

import java.net.URL;

/**
 * Factory for creating requests related to oEmbed information.
 */
@RequiredArgsConstructor
public class OEmbedRequestFactory {
    private final OEmbedService oEmbedService;

    /**
     * Creates a request to get oEmbed information for a given URL.
     *
     * @param url the URL to get oEmbed information for
     * @return a request to get oEmbed information for the given URL
     */
    public DeezerRequest<OEmbed> getOEmbed(URL url) {
        return new OEmbedDeezerRequest(url, oEmbedService);
    }
}
