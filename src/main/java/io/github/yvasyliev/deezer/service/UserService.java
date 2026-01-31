package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.annotation.Experimental;
import io.github.yvasyliev.deezer.feign.ListExpander;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Track;

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
     * Adds a notification to the user's feed.
     *
     * @param userId      user ID
     * @param accessToken Deezer access token
     * @param message     notification to add in the user feed
     * @return {@code true} if the notification was added successfully
     */
    @RequestLine("POST /user/{userId}/notifications")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<Boolean> addNotification(
            @Param("userId") String userId,
            @Param("access_token") String accessToken,
            @Param("message") String message
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
}
