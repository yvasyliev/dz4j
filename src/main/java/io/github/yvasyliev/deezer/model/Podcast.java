package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

/**
 * A podcast object.
 *
 * @param id            the podcast's Deezer id
 * @param title         the podcast's title
 * @param description   the podcast's description
 * @param available     if the podcast is available or not
 * @param fans          the number of podcast's fans
 * @param link          the url of the podcast on Deezer
 * @param share         the share link of the podcast on Deezer
 * @param picture       the url of the podcast's cover.
 * @param pictureSmall  the url of the podcast's cover in size small.
 * @param pictureMedium the url of the podcast's cover in size medium.
 * @param pictureBig    the url of the podcast's cover in size big.
 * @param pictureXl     the url of the podcast's cover in size xl.
 * @param position      the position of the podcasts in the charts
 */
public record Podcast(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("available") Boolean available,
        @JsonProperty("fans") Integer fans,
        @JsonProperty("link") URL link,
        @JsonProperty("share") URL share,
        @JsonProperty("picture") URL picture,
        @JsonProperty("picture_small") URL pictureSmall,
        @JsonProperty("picture_medium") URL pictureMedium,
        @JsonProperty("picture_big") URL pictureBig,
        @JsonProperty("picture_xl") URL pictureXl,
        @JsonProperty("position") Integer position
) {}
