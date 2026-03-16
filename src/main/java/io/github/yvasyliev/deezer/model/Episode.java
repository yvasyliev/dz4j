package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;
import lombok.Builder;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * An episode object.
 *
 * @param id            the episode's Deezer id
 * @param title         the episode's title
 * @param description   the episode's description
 * @param available     if the episode is available or not
 * @param releaseDate   the episode's release date
 * @param duration      the episode's duration (seconds)
 * @param link          the url of the episode on Deezer
 * @param share         the share link of the episode on Deezer
 * @param picture       the url of the episode's cover.
 * @param pictureSmall  the url of the episode's cover in size small.
 * @param pictureMedium the url of the episode's cover in size medium.
 * @param pictureBig    the url of the episode's cover in size big.
 * @param pictureXl     the url of the episode's cover in size xl.
 * @param trackToken    the track token for media service
 * @param podcast       the parent {@link Podcast} object
 */
@Builder
@Experimental
public record Episode(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("available") Boolean available,
        @JsonProperty("release_date") LocalDateTime releaseDate,
        @JsonProperty("duration") Duration duration,
        @JsonProperty("link") URL link,
        @JsonProperty("share") URL share,
        @JsonProperty("picture") URL picture,
        @JsonProperty("picture_small") URL pictureSmall,
        @JsonProperty("picture_medium") URL pictureMedium,
        @JsonProperty("picture_big") URL pictureBig,
        @JsonProperty("picture_xl") URL pictureXl,
        @JsonProperty("track_token") String trackToken,
        @JsonProperty("podcast") Podcast podcast
) {}
