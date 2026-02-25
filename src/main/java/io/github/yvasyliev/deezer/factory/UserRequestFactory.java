package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.authorization.AuthorizationContext;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.NotificationResult;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.request.CreatePlaylistDeezerRequest;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.GetByUserIdPagingDeezerRequest;
import io.github.yvasyliev.deezer.request.PagingDeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.service.UserService;
import io.github.yvasyliev.deezer.util.TriFunction;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class UserRequestFactory {
    private static final String ME = "me";
    private final UserService userService;
    private final AuthorizationContext authorizationContext;

    //region addAlbums

    /**
     * Creates a request to add albums to the user's library.
     *
     * @param albumIds the IDs of the albums to add
     * @return a request that, when executed, will add the specified albums to the user's library
     */
    public DeezerRequest<Boolean> addAlbums(Long... albumIds) {
        return addAlbums(Arrays.asList(albumIds));
    }

    /**
     * Creates a request to add albums to the user's library.
     *
     * @param albumIds the IDs of the albums to add
     * @return a request that, when executed, will add the specified albums to the user's library
     */
    public DeezerRequest<Boolean> addAlbums(Collection<Long> albumIds) {
        return addAlbums(ME, albumIds);
    }

    /**
     * Creates a request to add albums to a specific user's library.
     *
     * @param userId   the user ID
     * @param albumIds the IDs of the albums to add
     * @return a request that, when executed, will add the specified albums to the user's library
     */
    public DeezerRequest<Boolean> addAlbums(long userId, Long... albumIds) {
        return addAlbums(userId, Arrays.asList(albumIds));
    }

    /**
     * Creates a request to add albums to a specific user's library.
     *
     * @param userId   the user ID
     * @param albumIds the IDs of the albums to add
     * @return a request that, when executed, will add the specified albums to the user's library
     */
    public DeezerRequest<Boolean> addAlbums(long userId, Collection<Long> albumIds) {
        return addAlbums(String.valueOf(userId), albumIds);
    }

    private DeezerRequest<Boolean> addAlbums(String userId, Collection<Long> albumIds) {
        return createUserDeezerRequest(userId, albumIds, userService::addAlbums);
    }

    //endregion

    //region addArtists

    /**
     * Creates a request to add artists to the user's favorites.
     *
     * @param artistIds the IDs of the artists to add
     * @return a request that, when executed, will add the specified artists to the user's favorites
     */
    public DeezerRequest<Boolean> addArtists(Long... artistIds) {
        return addArtists(Arrays.asList(artistIds));
    }

    /**
     * Creates a request to add artists to the user's favorites.
     *
     * @param artistIds the IDs of the artists to add
     * @return a request that, when executed, will add the specified artists to the user's favorites
     */
    public DeezerRequest<Boolean> addArtists(Collection<Long> artistIds) {
        return addArtists(ME, artistIds);
    }

    /**
     * Creates a request to add artists to a specific user's favorites.
     *
     * @param userId    the user ID
     * @param artistIds the IDs of the artists to add
     * @return a request that, when executed, will add the specified artists to the user's favorites
     */
    public DeezerRequest<Boolean> addArtists(long userId, Long... artistIds) {
        return addArtists(userId, Arrays.asList(artistIds));
    }

    /**
     * Creates a request to add artists to a specific user's favorites.
     *
     * @param userId    the user ID
     * @param artistIds the IDs of the artists to add
     * @return a request that, when executed, will add the specified artists to the user's favorites
     */
    public DeezerRequest<Boolean> addArtists(long userId, Collection<Long> artistIds) {
        return addArtists(String.valueOf(userId), artistIds);
    }

    private DeezerRequest<Boolean> addArtists(String userId, Collection<Long> artistIds) {
        return createUserDeezerRequest(userId, artistIds, userService::addArtists);
    }

    //endregion

    //region addNotification

    /**
     * Creates a request to add a notification to the user's feed.
     *
     * @param message the message of the notification to add
     * @return a request that, when executed, will add the specified notification to the user's feed
     */
    public DeezerRequest<NotificationResult> addNotification(String message) {
        return addNotification(ME, message);
    }

    /**
     * Creates a request to add a notification to a user's feed.
     *
     * @param userId  the user ID
     * @param message the message of the notification to add
     * @return a request that, when executed, will add the specified notification to the user's feed
     */
    public DeezerRequest<NotificationResult> addNotification(long userId, String message) {
        return addNotification(String.valueOf(userId), message);
    }

    private DeezerRequest<NotificationResult> addNotification(String userId, String message) {
        return createUserDeezerRequest(userId, message, userService::addNotification);
    }

    //endregion

    //region addPlaylists

    /**
     * Creates a request to add playlists to the user's favorites.
     *
     * @param playlistIds the IDs of the playlists to add
     * @return a request that, when executed, will add the specified playlists to the user's favorites
     */
    public DeezerRequest<Boolean> addPlaylists(Long... playlistIds) {
        return addPlaylists(Arrays.asList(playlistIds));
    }

    /**
     * Creates a request to add playlists to the user's favorites.
     *
     * @param playlistIds the IDs of the playlists to add
     * @return a request that, when executed, will add the specified playlists to the user's favorites
     */
    public DeezerRequest<Boolean> addPlaylists(Collection<Long> playlistIds) {
        return addPlaylists(ME, playlistIds);
    }

    /**
     * Creates a request to add playlists to a specific user's favorites.
     *
     * @param userId      the user ID
     * @param playlistIds the IDs of the playlists to add
     * @return a request that, when executed, will add the specified playlists to the user's favorites
     */
    public DeezerRequest<Boolean> addPlaylists(long userId, Long... playlistIds) {
        return addPlaylists(userId, Arrays.asList(playlistIds));
    }

    /**
     * Creates a request to add playlists to a specific user's favorites.
     *
     * @param userId      the user ID
     * @param playlistIds the IDs of the playlists to add
     * @return a request that, when executed, will add the specified playlists to the user's favorites
     */
    public DeezerRequest<Boolean> addPlaylists(long userId, Collection<Long> playlistIds) {
        return addPlaylists(String.valueOf(userId), playlistIds);
    }

    private DeezerRequest<Boolean> addPlaylists(String userId, Collection<Long> playlistIds) {
        return createUserDeezerRequest(userId, playlistIds, userService::addPlaylists);
    }

    //endregion

    //region addPodcast

    /**
     * Creates a request to add a podcast to the user's favorites.
     *
     * @param podcastId the ID of the podcast to add
     * @return a request that, when executed, will add the specified podcast to the user's favorites
     */
    public DeezerRequest<Boolean> addPodcast(long podcastId) {
        return addPodcast(ME, podcastId);
    }

    /**
     * Creates a request to add a podcast to a specific user's favorites.
     *
     * @param userId    the user ID
     * @param podcastId the ID of the podcast to add
     * @return a request that, when executed, will add the specified podcast to the user's favorites
     */
    public DeezerRequest<Boolean> addPodcast(long userId, long podcastId) {
        return addPodcast(String.valueOf(userId), podcastId);
    }

    private DeezerRequest<Boolean> addPodcast(String userId, long podcastId) {
        return createUserDeezerRequest(userId, podcastId, userService::addPodcast);
    }

    //endregion

    //region addRadio

    /**
     * Creates a request to add a radio to the user's favorites.
     *
     * @param radioId the ID of the radio to add
     * @return a request that, when executed, will add the specified radio to the user's favorites
     */
    public DeezerRequest<Boolean> addRadio(long radioId) {
        return addRadio(ME, radioId);
    }

    /**
     * Creates a request to add a radio to a specific user's favorites.
     *
     * @param userId  the user ID
     * @param radioId the ID of the radio to add
     * @return a request that, when executed, will add the specified radio to the user's favorites
     */
    public DeezerRequest<Boolean> addRadio(long userId, long radioId) {
        return addRadio(String.valueOf(userId), radioId);
    }

    private DeezerRequest<Boolean> addRadio(String userId, long radioId) {
        return createUserDeezerRequest(userId, radioId, userService::addRadio);
    }

    //endregion

    //region addTracks

    /**
     * Creates a request to add tracks to the user's favorites.
     *
     * @param trackIds the IDs of the tracks to add
     * @return a request that, when executed, will add the specified tracks to the user's favorites
     */
    public DeezerRequest<Boolean> addTracks(Long... trackIds) {
        return addTracks(Arrays.asList(trackIds));
    }

    /**
     * Creates a request to add tracks to the user's favorites.
     *
     * @param trackIds the IDs of the tracks to add
     * @return a request that, when executed, will add the specified tracks to the user's favorites
     */
    public DeezerRequest<Boolean> addTracks(Collection<Long> trackIds) {
        return addTracks(ME, trackIds);
    }

    /**
     * Creates a request to add tracks to a specific user's favorites.
     *
     * @param userId   the user ID
     * @param trackIds the IDs of the tracks to add
     * @return a request that, when executed, will add the specified tracks to the user's favorites
     */
    public DeezerRequest<Boolean> addTracks(long userId, Long... trackIds) {
        return addTracks(userId, Arrays.asList(trackIds));
    }

    /**
     * Creates a request to add tracks to a specific user's favorites.
     *
     * @param userId   the user ID
     * @param trackIds the IDs of the tracks to add
     * @return a request that, when executed, will add the specified tracks to the user's favorites
     */
    public DeezerRequest<Boolean> addTracks(long userId, Collection<Long> trackIds) {
        return addTracks(String.valueOf(userId), trackIds);
    }

    private DeezerRequest<Boolean> addTracks(String userId, Collection<Long> trackIds) {
        return createUserDeezerRequest(userId, trackIds, userService::addTracks);
    }

    //endregion

    //region createPlaylist

    /**
     * Creates a request to create a new playlist for the user.
     *
     * @param title the title of the playlist to create
     * @return a request that, when executed, will create a new playlist for the user
     */
    public CreatePlaylistDeezerRequest createPlaylist(String title) {
        return createPlaylist(ME, title);
    }

    /**
     * Creates a request to create a new playlist for a specific user.
     *
     * @param userId the user ID
     * @param title  the title of the playlist to create
     * @return a request that, when executed, will create a new playlist for the user
     */
    public CreatePlaylistDeezerRequest createPlaylist(long userId, String title) {
        return createPlaylist(String.valueOf(userId), title);
    }

    private CreatePlaylistDeezerRequest createPlaylist(String userId, String title) {
        return new CreatePlaylistDeezerRequest(userId, authorizationContext, title, userService);
    }

    //endregion

    //region followUser

    /**
     * Creates a request to follow another user.
     *
     * @param followeeId the ID of the user to follow
     * @return a request that, when executed, will follow the specified user
     */
    public DeezerRequest<Boolean> followUser(long followeeId) {
        return followUser(ME, followeeId);
    }

    /**
     * Creates a request to follow another user.
     *
     * @param userId     the user ID
     * @param followeeId the ID of the user to follow
     * @return a request that, when executed, will follow the specified user
     */
    public DeezerRequest<Boolean> followUser(long userId, long followeeId) {
        return followUser(String.valueOf(userId), followeeId);
    }

    private DeezerRequest<Boolean> followUser(String userId, long followeeId) {
        return createUserDeezerRequest(userId, followeeId, userService::followUser);
    }

    //endregion

    //region getAlbumChart

    /**
     * Creates a request to get the user's top albums.
     *
     * @return a request that, when executed, will return the user's top albums
     */
    public PagingDeezerRequest<Page<Album>> getAlbumChar() {
        return getAlbumChart(ME);
    }

    /**
     * Creates a request to get a user's top albums.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return the user's top albums
     */
    public PagingDeezerRequest<Page<Album>> getAlbumChart(long userId) {
        return getAlbumChart(String.valueOf(userId));
    }

    private PagingDeezerRequest<Page<Album>> getAlbumChart(String userId) {
        return new GetByUserIdPagingDeezerRequest<>(userId, authorizationContext, userService::getAlbumChart);
    }

    //endregion

    private <T, R> DeezerRequest<R> createUserDeezerRequest(
            String userId,
            T argument,
            TriFunction<String, AccessToken, T, CompletableFuture<R>> asyncMethod
    ) {
        return new SimpleDeezerRequest<>(() -> asyncMethod.apply(
                userId,
                authorizationContext.getAccessTokenProvider().getAccessToken(),
                argument
        ));
    }
}
