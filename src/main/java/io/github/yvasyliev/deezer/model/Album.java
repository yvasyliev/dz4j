package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;
import lombok.Builder;
import lombok.Singular;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 * An album object.
 *
 * @param id                    the Deezer album id
 * @param title                 the album title
 * @param upc                   the album UPC
 * @param link                  the url of the album on Deezer
 * @param share                 the share link of the album on Deezer
 * @param cover                 the url of the album's cover. Add 'size' parameter to the url to change size. Can be
 *                              {@code small}, {@code medium}, {@code big}, {@code xl}
 * @param coverSmall            the url of the album's cover in size small
 * @param coverMedium           the url of the album's cover in size medium
 * @param coverBig              the url of the album's cover in size big
 * @param coverXl               the url of the album's cover in size xl
 * @param md5Image              the album cover's md5 hash
 * @param genreId               the album's first genre id (You should use the genre list instead). NB : {@code -1} for
 *                              not found
 * @param genres                list of {@link Genre} object
 * @param label                 the album's label name
 * @param provider              the album's provider name
 * @param nbTracks              the number of tracks in the album
 * @param duration              the album's duration (seconds)
 * @param fans                  the number of album's Fans
 * @param releaseDate           the album's release date
 * @param recordType            the record type of the album (EP / ALBUM / etc..)
 * @param available             boolean
 * @param alternative           return an alternative album object if the current album is not available
 * @param tracklist             API Link to the tracklist of this album
 * @param explicitLyrics        whether the album contains explicit lyrics
 * @param explicitContentLyrics the explicit content lyrics values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited;
 *                              4:Partially Explicit (Album "lyrics" only); 5:Partially Unknown (Album "lyrics" only);
 *                              6:No Advice Available; 7:Partially No Advice Available (Album "lyrics" only))
 * @param explicitContentCover  the explicit cover values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 4:Partially
 *                              Explicit (Album "lyrics" only); 5:Partially Unknown (Album "lyrics" only); 6:No Advice
 *                              Available; 7:Partially No Advice Available (Album "lyrics" only))
 * @param contributors          return a list of contributors on the album
 * @param fallback              return fallback album with id and status
 * @param artist                {@link Artist} object containing: {@code id}, {@code name}, {@code picture},
 *                              {@code picture_small}, {@code picture_medium}, {@code picture_big}, {@code picture_xl}
 * @param tracks                list of {@link Track}
 */
@Builder
public record Album(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("upc") String upc,
        @JsonProperty("link") URL link,
        @JsonProperty("share") URL share,
        @JsonProperty("cover") URL cover,
        @JsonProperty("cover_small") URL coverSmall,
        @JsonProperty("cover_medium") URL coverMedium,
        @JsonProperty("cover_big") URL coverBig,
        @JsonProperty("cover_xl") URL coverXl,
        @JsonProperty("md5_image") String md5Image,
        @JsonProperty("genre_id") Long genreId,
        @JsonProperty("genres") Page<Genre> genres,
        @JsonProperty("label") String label,
        @JsonProperty("provider") String provider,
        @JsonProperty("nb_tracks") Integer nbTracks,
        @JsonProperty("duration") Duration duration,
        @JsonProperty("fans") Integer fans,
        @JsonProperty("release_date") LocalDate releaseDate,
        @JsonProperty("record_type") RecordType recordType,
        @JsonProperty("available") @Experimental Boolean available,
        @JsonProperty("alternative") Album alternative,
        @JsonProperty("tracklist") URL tracklist,
        @JsonProperty("explicit_lyrics") Boolean explicitLyrics,
        @JsonProperty("explicit_content_lyrics") ExplicitContent explicitContentLyrics,
        @JsonProperty("explicit_content_cover") ExplicitContent explicitContentCover,
        @JsonProperty("contributors") @Singular List<Artist> contributors,
        @JsonProperty("fallback") Album fallback,
        @JsonProperty("artist") Artist artist,
        @JsonProperty("tracks") Page<Track> tracks
) {
    /**
     * The record type values.
     */
    @Experimental
    public enum RecordType {
        /**
         * Album.
         */
        @JsonProperty("album")
        ALBUM,

        /**
         * Compilation.
         */
        @JsonProperty("compilation")
        COMPILATION,

        /**
         * EP.
         */
        @JsonProperty("ep")
        EP,

        /**
         * Single.
         */
        @JsonProperty("single")
        SINGLE
    }
}
