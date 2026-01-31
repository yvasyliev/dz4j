package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

/**
 * An artist object.
 *
 * @param id            the artist's Deezer id
 * @param name          the artist's name
 * @param link          the URL of the artist on Deezer
 * @param share         the share link of the artist on Deezer
 * @param picture       the URL of the artist picture; add {@code size} parameter to change size
 * @param pictureSmall  the URL of the artist picture in size small
 * @param pictureMedium the URL of the artist picture in size medium
 * @param pictureBig    the URL of the artist picture in size big
 * @param pictureXl     the URL of the artist picture in size xl
 * @param nbAlbum       the number of artist's albums
 * @param nbFan         the number of artist's fans
 * @param radio         {@code true} if the artist has a smartradio
 * @param tracklist     API link to the top of this artist
 * @param role          the role of the artist in the context of a track contribution
 */
public record Artist(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("link") URL link,
        @JsonProperty("share") URL share,
        @JsonProperty("picture") URL picture,
        @JsonProperty("picture_small") URL pictureSmall,
        @JsonProperty("picture_medium") URL pictureMedium,
        @JsonProperty("picture_big") URL pictureBig,
        @JsonProperty("picture_xl") URL pictureXl,
        @JsonProperty("nb_album") Integer nbAlbum,
        @JsonProperty("nb_fan") Integer nbFan,
        @JsonProperty("radio") Boolean radio,
        @JsonProperty("tracklist") URL tracklist,
        @JsonProperty("role") Role role
) {}
