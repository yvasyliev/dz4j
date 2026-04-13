package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.net.URL;

/**
 * An oEmbed object.
 *
 * @param version         the oEmbed version number
 * @param type            the oEmbed resource type
 * @param cacheAge        the recommended number of seconds to cache the response
 * @param providerName    the name of the resource provider
 * @param providerUrl     the URL of the resource provider
 * @param entity          the Deezer resource type
 * @param id              the Deezer ID of the resource
 * @param url             the URL of the resource
 * @param authorName      the name of the author/owner of the resource
 * @param title           a text title, describing the resource
 * @param thumbnailUrl    a URL to a thumbnail image representing the resource
 * @param thumbnailWidth  the width of the thumbnail
 * @param thumbnailHeight the height of the thumbnail
 * @param width           the width in pixels required to display the HTML
 * @param height          the height in pixels required to display the HTML
 * @param html            the HTML required to display the resource
 */
@Builder
public record OEmbed(
        @JsonProperty("version") String version,
        @JsonProperty("type") Type type,
        @JsonProperty("cache_age") Integer cacheAge,
        @JsonProperty("provider_name") String providerName,
        @JsonProperty("provider_url") URL providerUrl,
        @JsonProperty("entity") Entity entity,
        @JsonProperty("id") Long id,
        @JsonProperty("url") URL url,
        @JsonProperty("author_name") String authorName,
        @JsonProperty("title") String title,
        @JsonProperty("thumbnail_url") URL thumbnailUrl,
        @JsonProperty("thumbnail_width") Integer thumbnailWidth,
        @JsonProperty("thumbnail_height") Integer thumbnailHeight,
        @JsonProperty("width") Integer width,
        @JsonProperty("height") Integer height,
        @JsonProperty("html") String html
) {
    /**
     * The oEmbed resource type.
     */
    public enum Type {
        /**
         * Rich oEmbed resource.
         */
        @JsonProperty("rich") RICH
    }

    /**
     * The Deezer resource type.
     */
    public enum Entity {
        /**
         * Album resource.
         */
        @JsonProperty("album") ALBUM,

        /**
         * Episode resource.
         */
        @JsonProperty("episode") EPISODE,

        /**
         * Playlist resource.
         */
        @JsonProperty("playlist") PLAYLIST,

        /**
         * Show resource.
         */
        @JsonProperty("show") SHOW,

        /**
         * Track resource.
         */
        @JsonProperty("track") TRACK
    }
}
