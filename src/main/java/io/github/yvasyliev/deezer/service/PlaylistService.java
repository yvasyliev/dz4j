package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.annotation.Experimental;
import io.github.yvasyliev.deezer.feign.ListExpander;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface PlaylistService {
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
     * Adds a track to the playlist.
     *
     * @param playlistId  playlist ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param trackIds    collection of track IDs to add
     * @return {@code true} if the tracks were added successfully
     */
    @RequestLine("POST /playlist/{playlistId}/tracks") //TODO: CollectionFormat
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addTracks(
            @Param("playlistId") long playlistId,
            @Param("access_token") String accessToken,
            @Param(value = "songs", expander = ListExpander.class) Collection<Long> trackIds
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
            @Param(value = "order", expander = ListExpander.class) List<Long> trackIds
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
    @RequestLine("DELETE /playlist/{playlistId}/tracks?access_token={accessToken}&songs={songs}")
    CompletableFuture<Boolean> deleteTracks(
            @Param("playlistId") long playlistId,
            @Param("accessToken") String accessToken,
            @Param(value = "songs", expander = ListExpander.class) Collection<Long> trackIds
    );
}
