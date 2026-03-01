package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.Track;

import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface TrackService {
    /**
     * Returns a track.
     *
     * @param trackId the track ID
     * @return a track
     */
    @RequestLine("GET /track/{trackId}")
    CompletableFuture<Track> getTrack(@Param("trackId") long trackId);

    /**
     * Updates a user's track information.
     *
     * @param trackId     the track ID
     * @param accessToken Deezer access token
     * @param title       the new title of the track
     * @param artist      the new artist of the track
     * @param album       the new album of the track
     * @return {@code true} if the track was updated successfully
     */
    @RequestLine("POST /track/{trackId}")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> updateTrack(
            @Param("trackId") long trackId,
            @Param("access_token") String accessToken,
            @Param("title") String title,
            @Param("artist") String artist,
            @Param("album") String album
    );
}
