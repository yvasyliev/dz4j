package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Singular;

import java.net.URL;
import java.util.List;

/**
 * A genre object.
 *
 * @param id            the genre id
 * @param name          the genre name
 * @param picture       the url of the genre picture. Add {@code size} parameter to the url to change size. Can be
 *                      {@code small}, {@code medium}, {@code big}, {@code xl}
 * @param pictureSmall  the url of the genre picture in size small.
 * @param pictureMedium the url of the genre picture in size medium.
 * @param pictureBig    the url of the genre picture in size big.
 * @param pictureXl     the url of the genre picture in size xl.
 * @param radios        the list of radios associated with this genre
 */
@Builder
public record Genre(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("picture") URL picture,
        @JsonProperty("picture_small") URL pictureSmall,
        @JsonProperty("picture_medium") URL pictureMedium,
        @JsonProperty("picture_big") URL pictureBig,
        @JsonProperty("picture_xl") URL pictureXl,
        @JsonProperty("radios") @Singular List<Radio> radios
) {}
