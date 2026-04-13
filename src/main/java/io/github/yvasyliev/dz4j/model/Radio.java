package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.dz4j.annotation.Experimental;
import lombok.Builder;

import java.net.URL;

/**
 * A radio object.
 *
 * @param id            the radio deezer ID
 * @param title         the radio title
 * @param description   the radio title
 * @param share         the share link of the radio on Deezer
 * @param picture       the url of the radio picture. Add {@code size} parameter to the url to change size. Can be
 *                      {@code small}, {@code medium}, {@code big}, {@code xl}
 * @param pictureSmall  the url of the radio picture in size small
 * @param pictureMedium the url of the radio picture in size medium
 * @param pictureBig    the url of the radio picture in size big
 * @param pictureXl     the url of the radio picture in size xl
 * @param tracklist     api Link to the tracklist of this radio
 * @param md5Image      the radio picture's md5 hash
 */
@Builder
public record Radio(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("share") URL share,
        @JsonProperty("picture") URL picture,
        @JsonProperty("picture_small") URL pictureSmall,
        @JsonProperty("picture_medium") URL pictureMedium,
        @JsonProperty("picture_big") URL pictureBig,
        @JsonProperty("picture_xl") URL pictureXl,
        @JsonProperty("tracklist") URL tracklist,
        @JsonProperty("md5_image") @Experimental String md5Image
) {}
