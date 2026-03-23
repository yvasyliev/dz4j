package io.github.yvasyliev.deezer.service;

import feign.CollectionFormat;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.annotation.Experimental;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Service for working with playlists on Deezer.
 */
@Headers("Accept: application/json")
public interface PlaylistService {
    /**
     * Adds a track to the playlist.
     *
     * @param playlistId  playlist ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param trackIds    collection of track IDs to add
     * @return {@code true} if the tracks were added successfully
     */
    @RequestLine("POST /playlist/{playlistId}/tracks")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addTracks(
            @Param("playlistId") long playlistId,
            @Param("access_token") String accessToken,
            @Param("songs") Collection<Long> trackIds
    );

    /**
     * Deletes the playlist.
     *
     * @param playlistId  playlist ID
     * @param accessToken Deezer access token with {@code delete_library} permission
     * @return {@code true} if the playlist was deleted successfully
     */
    @RequestLine("DELETE /playlist/{playlistId}?access_token={accessToken}")
    CompletableFuture<Boolean> deletePlaylist(
            @Param("playlistId") long playlistId,
            @Param("accessToken") String accessToken
    );

    /**
     * Deletes tracks from the playlist.
     *
     * @param playlistId  playlist ID
     * @param accessToken Deezer access token with {@code manage_library} and {@code delete_library} permissions
     * @param trackIds    collection of track IDs to delete
     * @return {@code true} if the tracks were deleted successfully
     */
    @RequestLine(
            value = "DELETE /playlist/{playlistId}/tracks?access_token={accessToken}&songs={songs}",
            collectionFormat = CollectionFormat.CSV
    )
    CompletableFuture<Boolean> deleteTracks(
            @Param("playlistId") long playlistId,
            @Param("accessToken") String accessToken,
            @Param("songs") Collection<Long> trackIds
    );

    /**
     * Returns a list of playlist's fans.
     *
     * @param playlistId playlist ID
     * @param index      the offset of the first object
     * @param limit      the maximum number of objects to return
     * @return list of users who are fans of the playlist
     */
    @RequestLine("GET /playlist/{playlistId}/fans?index={index}&limit={limit}")
    CompletableFuture<Page<User>> getFans(
            @Param("playlistId") long playlistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a playlist.
     *
     * @param playlistId playlist ID
     * @return playlist
     */
    @RequestLine("GET /playlist/{playlistId}")
    CompletableFuture<Playlist> getPlaylist(@Param("playlistId") long playlistId);

    /**
     * Returns a list of playlist's recommendation tracks.
     *
     * @param playlistId playlist ID
     * @param index      the offset of the first object
     * @param limit      the maximum number of objects to return
     * @return list of recommendation tracks for the playlist
     */
    @RequestLine("GET /playlist/{playlistId}/radio?index={index}&limit={limit}")
    CompletableFuture<Optional<Page<Track>>> getRadio(
            @Param("playlistId") long playlistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of playlist's tracks.
     *
     * @param playlistId playlist ID
     * @param index      the offset of the first object
     * @param limit      the maximum number of objects to return
     * @return list of playlist's tracks
     */
    @RequestLine("GET /playlist/{playlistId}/tracks?index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getTracks(
            @Param("playlistId") long playlistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Marks the playlist as seen.
     *
     * @param playlistId  playlist ID
     * @param accessToken Deezer access token with {@code basic_access} permission
     * @return {@code true} if the playlist was marked as seen successfully
     */
    @RequestLine("POST /playlist/{playlistId}/seen")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> markAsSeen(
            @Param("playlistId") long playlistId,
            @Param("access_token") String accessToken
    );

    /**
     * Orders tracks in the playlist.
     *
     * @param playlistId  playlist ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param trackIds    list of track IDs in the desired order
     * @return {@code true} if the tracks were ordered successfully
     */
    @RequestLine("POST /playlist/{playlistId}/tracks")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> orderTracks(
            @Param("playlistId") long playlistId,
            @Param("access_token") String accessToken,
            @Param("order") Collection<Long> trackIds
    );

    /**
     * Updates the playlist.
     *
     * @param playlistId    playlist ID
     * @param accessToken   Deezer access token
     * @param title         playlist title
     * @param description   playlist description
     * @param isPublic      whether the playlist is public
     * @param collaborative whether the playlist is collaborative
     * @return {@code true} if the playlist was updated successfully
     */
    @RequestLine("POST /playlist/{playlistId}")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> updatePlaylist(
            @Param("playlistId") long playlistId,
            @Param("access_token") String accessToken,
            @Param("title") @Experimental String title,
            @Param("description") @Experimental String description,
            @Param("public") @Experimental Boolean isPublic,
            @Param("collaborative") @Experimental Boolean collaborative
    );
}
