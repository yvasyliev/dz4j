package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;
import lombok.Builder;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * A playlist object.
 *
 * @param id               the playlist's Deezer id
 * @param title            the playlist's title
 * @param description      the playlist description
 * @param duration         the playlist's duration (seconds)
 * @param isPublic         if the playlist is public or not
 * @param isLovedTrack     if the playlist is the love tracks playlist
 * @param collaborative    if the playlist is collaborative or not
 * @param nbTracks         nb tracks in the playlist
 * @param unseenTrackCount nb tracks not seen
 * @param fans             the number of playlist's fans
 * @param link             the url of the playlist on Deezer
 * @param share            the share link of the playlist on Deezer
 * @param picture          the url of the playlist's cover. Add {@code size} parameter to the url to change size. Can be
 *                         {@code small}, {@code medium}, {@code big}, {@code xl}
 * @param pictureSmall     the url of the playlist's cover in size small.
 * @param pictureMedium    the url of the playlist's cover in size medium.
 * @param pictureBig       the url of the playlist's cover in size big.
 * @param pictureXl        the url of the playlist's cover in size xl.
 * @param checksum         the checksum for the track list
 * @param tracklist        API url to access the playlist's tracks
 * @param creationDate     the date when the playlist was created
 * @param addDate          the date when the playlist was added
 * @param modDate          the date when the playlist was last modified
 * @param md5Image         the MD5 hash of the playlist's cover image
 * @param pictureType      the type of the playlist's cover image
 * @param creator          {@link User} object containing: {@code id}, {@code name}
 * @param tracks           list of {@link Track}
 */
@Builder
public record Playlist(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("duration") Duration duration,
        @JsonProperty("public") Boolean isPublic,
        @JsonProperty("is_loved_track") Boolean isLovedTrack,
        @JsonProperty("collaborative") Boolean collaborative,
        @JsonProperty("nb_tracks") Integer nbTracks,
        @JsonProperty("unseen_track_count") Integer unseenTrackCount,
        @JsonProperty("fans") Integer fans,
        @JsonProperty("link") URL link,
        @JsonProperty("share") URL share,
        @JsonProperty("picture") URL picture,
        @JsonProperty("picture_small") URL pictureSmall,
        @JsonProperty("picture_medium") URL pictureMedium,
        @JsonProperty("picture_big") URL pictureBig,
        @JsonProperty("picture_xl") URL pictureXl,
        @JsonProperty("checksum") String checksum,
        @Experimental @JsonProperty("tracklist") URL tracklist,
        @Experimental @JsonProperty("creation_date") LocalDateTime creationDate,
        @Experimental @JsonProperty("add_date") LocalDateTime addDate,
        @Experimental @JsonProperty("mod_date") LocalDateTime modDate,
        @Experimental @JsonProperty("md5_image") String md5Image,
        @Experimental @JsonProperty("picture_type") PictureType pictureType,
        @JsonProperty("creator") User creator,
        @JsonProperty("tracks") Page<Track> tracks
) {
    /**
     * Type of the picture.
     */
    @Experimental
    public enum PictureType {
        /**
         * Cover picture type.
         */
        @JsonProperty("cover") COVER,

        /**
         * Playlist picture type.
         */
        @JsonProperty("playlist") PLAYLIST
    }
}
