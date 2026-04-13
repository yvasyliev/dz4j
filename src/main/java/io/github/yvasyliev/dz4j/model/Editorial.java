package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.net.URL;

/**
 * An editorial object.
 *
 * @param id            the editorial's Deezer id
 * @param name          the editorial's name
 * @param picture       the url of the editorial picture.
 * @param pictureSmall  the url of the editorial picture in size small.
 * @param pictureMedium the url of the editorial picture in size medium.
 * @param pictureBig    the url of the editorial picture in size big.
 * @param pictureXl     the url of the editorial picture in size xl.
 */
@Builder
public record Editorial(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("picture") URL picture,
        @JsonProperty("picture_small") URL pictureSmall,
        @JsonProperty("picture_medium") URL pictureMedium,
        @JsonProperty("picture_big") URL pictureBig,
        @JsonProperty("picture_xl") URL pictureXl
) {}
