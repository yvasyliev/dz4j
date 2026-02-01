package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.annotation.Experimental;
import io.github.yvasyliev.deezer.feign.ListExpander;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.NotificationResult;
import io.github.yvasyliev.deezer.model.Options;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Permissions;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Radio;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface UserService {

    /**
     * Adds album(s) to the user's library.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param albumIds    collection of album IDs to add
     * @return {@code true} if the albums were added successfully
     */
    @RequestLine("POST /user/{userId}/albums")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addAlbums(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param(value = "album_ids", expander = ListExpander.class) @Experimental Collection<Long> albumIds
    );

    /**
     * Adds artist(s) to the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param artistIds   collection of artist IDs to add
     * @return {@code true} if the artists were added successfully
     */
    @RequestLine("POST /user/{userId}/artists")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addArtists(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param(value = "artist_ids", expander = ListExpander.class) @Experimental Collection<Long> artistIds
    );

    /**
     * Adds a notification to the user's feed.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param message     notification to add in the user feed
     * @return the result of the operation
     */
    @RequestLine("POST /user/{userId}/notifications")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<NotificationResult> addNotification(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param("message") String message
    );

    /**
     * Adds playlist(s) to the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param playlistIds collection of playlist IDs to add
     * @return {@code true} if the playlists were added successfully
     */
    @RequestLine("POST /user/{userId}/playlists")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addPlaylists(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param(value = "playlist_ids", expander = ListExpander.class) Collection<Long> playlistIds
    );

    /**
     * Adds a podcast to the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param podcastId   the id of the podcast
     * @return {@code true} if the podcast was added successfully
     */
    @RequestLine("POST /user/{userId}/podcasts")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addPodcast(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param("podcast_id") long podcastId
    );

    /**
     * Adds a radio to the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param radioId     the id of the radio
     * @return {@code true} if the radio was added successfully
     */
    @RequestLine("POST /user/{userId}/radios")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addRadio(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param("radio_id") long radioId
    );

    /**
     * Adds track(s) to the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param trackIds    collection of track IDs to add
     * @return {@code true} if the tracks were added successfully
     */
    @RequestLine("POST /user/{userId}/tracks")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addTracks(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param(value = "track_ids", expander = ListExpander.class) Collection<Long> trackIds
    );

    /**
     * Creates a new playlist for the user.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} permission
     * @param title       the title of the new playlist
     * @param description the description of the new playlist
     * @return {@code true} if the playlist was created successfully
     */
    @RequestLine("POST /user/{userId}/playlists")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Playlist> createPlaylist(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param("title") String title,
            @Param("description") @Experimental String description
    );

    /**
     * Follows another user.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_community} permission
     * @param followeeId  the user ID to follow
     * @return {@code true} if the user was followed successfully
     */
    @RequestLine("POST /user/{userId}/followings")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> followUser(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param("user_id") String followeeId
    );

    /**
     * Returns a list of the user's top albums.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of albums
     */
    @RequestLine("GET /user/{userId}/charts/albums?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Album>> getAlbumChart(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of albums recommendations.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of album recommendations
     */
    @RequestLine("GET /user/{userId}/recommendations/albums?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Album>> getAlbumRecommendations(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of the user's top artists.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of artists
     */
    @RequestLine("GET /user/{userId}/charts/artists?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Artist>> getArtistChart(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of artists recommendations.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of artist recommendations
     */
    @RequestLine("GET /user/{userId}/recommendations/artists?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Artist>> getArtistRecommendations(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of the user's favorite artists.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of artists
     */
    @RequestLine("GET /user/{userId}/artists?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Artist>> getArtists(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of the user's top tracks.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of tracks
     */
    @RequestLine("GET /user/{userId}/charts?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getChart(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of user's flow tracks.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @return a page of tracks in the user's flow
     */
    @RequestLine("GET /user/{userId}/flow?access_token={accessToken}")
    CompletableFuture<Page<Track>> getFlow(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken
    );

    /**
     * Returns a list of the user's followers.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of users who follow the user
     */
    @RequestLine("GET /user/{userId}/followers?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<User>> getFollowers(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of users followed by the user.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of users followed by the user
     */
    @RequestLine("GET /user/{userId}/followings?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<User>> getFollowings(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of the user's listening history.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of tracks from the user's listening history
     */
    @RequestLine("GET /user/{userId}/history?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getHistory(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Gets the user's options.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @return user's options
     */
    @RequestLine("GET /user/{userId}/options?access_token={accessToken}")
    CompletableFuture<Options> getOptions(@Param("userId") String userId, @Param("accessToken") String accessToken);

    /**
     * Returns the user's {@link Permissions} granted to the application.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @return user's permissions
     */
    @RequestLine("GET /user/{userId}/permissions?access_token={accessToken}")
    CompletableFuture<Permissions> getPermissions(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken
    );

    /**
     * Returns a list of the user's personal songs.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of the user's personal songs
     */
    @RequestLine("GET /user/{userId}/personal_songs?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getPersonalSongs(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of the user's top playlists.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of playlists
     */
    @RequestLine("GET /user/{userId}/charts/playlists?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Playlist>> getPlaylistChart(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of playlists recommendations.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of playlist recommendations
     */
    @RequestLine("GET /user/{userId}/recommendations/playlists?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Playlist>> getPlaylistRecommendations(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of user's public {@link Playlist}.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of playlists
     */
    @RequestLine("GET /user/{userId}/playlists?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Playlist>> getPlaylists(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of radio recommendations.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of radio recommendations
     */
    @RequestLine("GET /user/{userId}/recommendations/radios?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Radio>> getRadioRecommendations(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of the user's favorite radios.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of radios
     */
    @RequestLine("GET /user/{userId}/radios?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Radio>> getRadios(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of release recommendations.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of release recommendations
     */
    @RequestLine("GET /user/{userId}/recommendations/releases?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Album>> getReleaseRecommendations(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of the user's top tracks.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of tracks
     */
    @RequestLine("GET /user/{userId}/charts/tracks?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getTrackChart(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of track recommendations.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @return a page of track recommendations
     */
    @RequestLine("GET /user/{userId}/recommendations/tracks?access_token={accessToken}")
    CompletableFuture<Page<Track>> getTrackRecommendations(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken
    );

    /**
     * Returns a list of user's favorite tracks.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of tracks
     */
    @RequestLine("GET /user/{userId}/tracks?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getTracks(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Gets the user information.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @return user information
     */
    @RequestLine("GET /user/{userId}?access_token={accessToken}")
    CompletableFuture<User> getUser(@Param("userId") String userId, @Param("accessToken") String accessToken);

    /**
     * Returns a list of the user's favorite albums.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param index       index of the first item to return
     * @param limit       maximum number of items to return
     * @return a page of albums
     */
    @RequestLine("GET /user/{userId}/albums?access_token={accessToken}&index={index}&limit={limit}")
    CompletableFuture<Page<Album>> getUserAlbums(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Removes an album from the user's library
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} and {@code delete_library} permission
     * @param albumId     the id of the album to delete
     * @return {@code true} if the album was deleted successfully
     */
    @RequestLine("DELETE /user/{userId}/albums?access_token={accessToken}&album_id={albumId}")
    CompletableFuture<Boolean> removeAlbum(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("albumId") long albumId
    );

    /**
     * Removes an artist from the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} and {@code delete_library} permission
     * @param artistId    the id of the artist to delete
     * @return {@code true} if the artist was deleted successfully
     */
    @RequestLine("DELETE /user/{userId}/artists?access_token={accessToken}&artist_id={artistId}")
    CompletableFuture<Boolean> removeArtist(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("artistId") long artistId
    );

    /**
     * Removes a playlist from the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} and {@code delete_library} permission
     * @param playlistId  the id of the playlist to delete
     * @return {@code true} if the playlist was deleted successfully
     */
    @RequestLine("DELETE /user/{userId}/playlists?access_token={accessToken}&playlist_id={playlistId}")
    CompletableFuture<Boolean> removePlaylist(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("playlistId") long playlistId
    );

    /**
     * Removes a podcast from the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} and {@code delete_library} permission
     * @param podcastId   the id of the podcast to delete
     * @return {@code true} if the podcast was deleted successfully
     */
    @RequestLine("DELETE /user/{userId}/podcasts?access_token={accessToken}&podcast_id={podcastId}")
    CompletableFuture<Boolean> removePodcast(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("podcastId") long podcastId
    );

    /**
     * Removes a radio from the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} and {@code delete_library} permission
     * @param radioId     the id of the radio to delete
     * @return {@code true} if the radio was deleted successfully
     */
    @RequestLine("DELETE /user/{userId}/radios?access_token={accessToken}&radio_id={radioId}")
    CompletableFuture<Boolean> removeRadio(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("radioId") long radioId
    );

    /**
     * Removes a track from the user's favorites.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_library} and {@code delete_library} permission
     * @param trackId     the id of the track to delete
     * @return {@code true} if the track was deleted successfully
     */
    @RequestLine("DELETE /user/{userId}/tracks?access_token={accessToken}&track_id={trackId}")
    CompletableFuture<Boolean> removeTrack(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("trackId") long trackId
    );

    /**
     * Unfollows another user.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token with {@code manage_community} permission
     * @param followeeId  the user ID to unfollow
     * @return {@code true} if the user was unfollowed successfully
     */
    @RequestLine("DELETE /user/{userId}/followings?access_token={accessToken}&user_id={followeeId}")
    CompletableFuture<Boolean> unfollowUser(
            @Param("userId") String userId,
            @Param("accessToken") String accessToken,
            @Param("followeeId") String followeeId
    );
}
