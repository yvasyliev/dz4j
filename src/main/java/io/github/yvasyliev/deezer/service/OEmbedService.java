package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.OEmbed;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface OEmbedService {
    /**
     * Returns the oEmbed information for a given URL.
     *
     * @param url       the URL to get the oEmbed information for
     * @param autoplay  whether to autoplay the content
     * @param maxWidth  the maximum width of the embedded content
     * @param maxHeight the maximum height of the embedded content
     * @param radius    whether to use rounded corners for the embedded content
     * @param tracklist whether to include a tracklist in the embedded content
     * @return the oEmbed information for the given URL
     */
    @RequestLine("GET /oembed?format=json"
            + "&url={url}"
            + "&autoplay={autoplay}"
            + "&maxwidth={maxWidth}"
            + "&maxheight={maxHeight}"
            + "&radius={radius}"
            + "&tracklist={tracklist}")
    CompletableFuture<OEmbed> getOEmbed(
            @Param("url") URL url,
            @Param("autoplay") Boolean autoplay,
            @Param("maxWidth") Integer maxWidth,
            @Param("maxHeight") Integer maxHeight,
            @Param("radius") Boolean radius,
            @Param("tracklist") Boolean tracklist
    );
}
