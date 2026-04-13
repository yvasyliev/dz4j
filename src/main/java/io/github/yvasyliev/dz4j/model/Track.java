package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.dz4j.annotation.Experimental;
import lombok.Builder;
import lombok.Singular;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

/**
 * A track object.
 *
 * @param id                    the track's Deezer id
 * @param readable              {@code true} if the track is readable in the player for the current user
 * @param title                 the track's full title
 * @param titleShort            the track's short title
 * @param titleVersion          the track's version
 * @param unseen                the track unseen status
 * @param isrc                  the track isrc
 * @param link                  the url of the track on Deezer
 * @param share                 the share link of the track on Deezer
 * @param duration              the track's duration in seconds
 * @param trackPosition         the position of the track in its album
 * @param diskNumber            the track's album's disk number
 * @param rank                  the track's Deezer rank
 * @param releaseDate           the track's release date
 * @param explicitLyrics        whether the track contains explicit lyrics
 * @param explicitContentLyrics the explicit content lyrics values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited;
 *                              6:No Advice Available)
 * @param explicitContentCover  the explicit cover value (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 6:No Advice
 *                              Available)
 * @param preview               the url of track's preview file. This file contains the first 30 seconds of the track.
 * @param bpm                   beats per minute
 * @param gain                  signal strength
 * @param availableCountries    list of countries where the track is available
 * @param alternative           return an alternative readable track if the current track is not readable
 * @param contributors          return a list of contributors on the track
 * @param md5Image              MD5 of the track image
 * @param trackToken            the track token for media service
 * @param artist                {@link Artist} object containing: {@code id}, {@code name}, {@code link}, {@code share},
 *                              {@code picture}, {@code picture_small}, {@code picture_medium}, {@code picture_big},
 *                              {@code picture_xl}, {@code nb_album}, {@code nb_fan}, {@code radio}, {@code tracklist},
 *                              {@code role}
 * @param album                 {@link Album} object containing: {@code id}, {@code title}, {@code link}, {@code cover},
 *                              {@code cover_small}, {@code cover_medium}, {@code cover_big}, {@code cover_xl},
 *                              {@code release_date}
 */
@Builder
public record Track(
        @JsonProperty("id") Long id,
        @JsonProperty("readable") Boolean readable,
        @JsonProperty("title") String title,
        @JsonProperty("title_short") String titleShort,
        @JsonProperty("title_version") String titleVersion,
        @JsonProperty("unseen") Boolean unseen,
        @JsonProperty("isrc") String isrc,
        @JsonProperty("link") URL link,
        @JsonProperty("share") URL share,
        @JsonProperty("duration") Duration duration,
        @JsonProperty("track_position") Integer trackPosition,
        @JsonProperty("disk_number") Integer diskNumber,
        @JsonProperty("rank") Integer rank,
        @JsonProperty("release_date") LocalDate releaseDate,
        @JsonProperty("explicit_lyrics") Boolean explicitLyrics,
        @JsonProperty("explicit_content_lyrics") ExplicitContent explicitContentLyrics,
        @JsonProperty("explicit_content_cover") ExplicitContent explicitContentCover,
        @JsonProperty("preview") URL preview,
        @JsonProperty("bpm") Float bpm,
        @JsonProperty("gain") Float gain,
        @JsonProperty("available_countries") EnumSet<Country> availableCountries,
        @JsonProperty("alternative") Track alternative,
        @JsonProperty("contributors") @Singular List<Artist> contributors,
        @JsonProperty("md5_image") @Experimental String md5Image,
        @JsonProperty("track_token") String trackToken,
        @JsonProperty("artist") Artist artist,
        @JsonProperty("album") Album album
) {}
