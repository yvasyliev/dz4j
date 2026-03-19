package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.NotificationResult;
import io.github.yvasyliev.deezer.model.Options;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.PermissionsResponse;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Radio;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.request.CreatePlaylistDeezerRequest;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.deezer.request.PagingDeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.service.UserService;
import io.github.yvasyliev.deezer.util.QuadFunction;
import io.github.yvasyliev.deezer.util.TriFunction;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * Factory for creating requests related to users.
 */
@RequiredArgsConstructor
public class UserRequestFactory {
    private final UserService userService;
    private final TokenManager<AccessToken> accessTokenManager;

    //region addAlbums

    /**
     * Creates a request to add album(s) to the user's library.
     *
     * @param albumIds the IDs of the albums to add
     * @return a request that, when executed, will add album(s) to the user's library
     */
    public DeezerRequest<Boolean> addAlbums(Long... albumIds) {
        return addAlbums(Arrays.asList(albumIds));
    }

    /**
     * Creates a request to add album(s) to the user's library.
     *
     * @param albumIds the IDs of the albums to add
     * @return a request that, when executed, will add album(s) to the user's library
     */
    public DeezerRequest<Boolean> addAlbums(Collection<Long> albumIds) {
        return addAlbums(UserId.ME, albumIds);
    }

    /**
     * Creates a request to add album(s) to a specific user's library.
     *
     * @param userId   the user ID
     * @param albumIds the IDs of the albums to add
     * @return a request that, when executed, will add album(s) to the user's library
     */
    public DeezerRequest<Boolean> addAlbums(long userId, Long... albumIds) {
        return addAlbums(userId, Arrays.asList(albumIds));
    }

    /**
     * Creates a request to add album(s) to a specific user's library.
     *
     * @param userId   the user ID
     * @param albumIds the IDs of the albums to add
     * @return a request that, when executed, will add album(s) to the user's library
     */
    public DeezerRequest<Boolean> addAlbums(long userId, Collection<Long> albumIds) {
        return addAlbums(UserId.of(userId), albumIds);
    }

    private DeezerRequest<Boolean> addAlbums(UserId userId, Collection<Long> albumIds) {
        return createDeezerRequest(userId, albumIds, userService::addAlbums);
    }

    //endregion

    //region addArtists

    /**
     * Creates a request to add artist(s) to the user's favorites.
     *
     * @param artistIds the IDs of the artists to add
     * @return a request that, when executed, will add artist(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addArtists(Long... artistIds) {
        return addArtists(Arrays.asList(artistIds));
    }

    /**
     * Creates a request to add artist(s) to the user's favorites.
     *
     * @param artistIds the IDs of the artists to add
     * @return a request that, when executed, will add artist(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addArtists(Collection<Long> artistIds) {
        return addArtists(UserId.ME, artistIds);
    }

    /**
     * Creates a request to add artist(s) to a specific user's favorites.
     *
     * @param userId    the user ID
     * @param artistIds the IDs of the artists to add
     * @return a request that, when executed, will add artist(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addArtists(long userId, Long... artistIds) {
        return addArtists(userId, Arrays.asList(artistIds));
    }

    /**
     * Creates a request to add artist(s) to a specific user's favorites.
     *
     * @param userId    the user ID
     * @param artistIds the IDs of the artists to add
     * @return a request that, when executed, will add artist(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addArtists(long userId, Collection<Long> artistIds) {
        return addArtists(UserId.of(userId), artistIds);
    }

    private DeezerRequest<Boolean> addArtists(UserId userId, Collection<Long> artistIds) {
        return createDeezerRequest(userId, artistIds, userService::addArtists);
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
        return addNotification(UserId.ME, message);
    }

    /**
     * Creates a request to add a notification to a user's feed.
     *
     * @param userId  the user ID
     * @param message the message of the notification to add
     * @return a request that, when executed, will add the specified notification to the user's feed
     */
    public DeezerRequest<NotificationResult> addNotification(long userId, String message) {
        return addNotification(UserId.of(userId), message);
    }

    private DeezerRequest<NotificationResult> addNotification(UserId userId, String message) {
        return createDeezerRequest(userId, message, userService::addNotification);
    }

    //endregion

    //region addPlaylists

    /**
     * Creates a request to add playlist(s) to the user's favorites.
     *
     * @param playlistIds the IDs of the playlists to add
     * @return a request that, when executed, will add playlist(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addPlaylists(Long... playlistIds) {
        return addPlaylists(Arrays.asList(playlistIds));
    }

    /**
     * Creates a request to add playlist(s) to the user's favorites.
     *
     * @param playlistIds the IDs of the playlists to add
     * @return a request that, when executed, will add playlist(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addPlaylists(Collection<Long> playlistIds) {
        return addPlaylists(UserId.ME, playlistIds);
    }

    /**
     * Creates a request to add playlist(s) to a specific user's favorites.
     *
     * @param userId      the user ID
     * @param playlistIds the IDs of the playlists to add
     * @return a request that, when executed, will add playlist(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addPlaylists(long userId, Long... playlistIds) {
        return addPlaylists(userId, Arrays.asList(playlistIds));
    }

    /**
     * Creates a request to add playlist(s) to a specific user's favorites.
     *
     * @param userId      the user ID
     * @param playlistIds the IDs of the playlists to add
     * @return a request that, when executed, will add playlist(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addPlaylists(long userId, Collection<Long> playlistIds) {
        return addPlaylists(UserId.of(userId), playlistIds);
    }

    private DeezerRequest<Boolean> addPlaylists(UserId userId, Collection<Long> playlistIds) {
        return createDeezerRequest(userId, playlistIds, userService::addPlaylists);
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
        return addPodcast(UserId.ME, podcastId);
    }

    /**
     * Creates a request to add a podcast to a specific user's favorites.
     *
     * @param userId    the user ID
     * @param podcastId the ID of the podcast to add
     * @return a request that, when executed, will add the specified podcast to the user's favorites
     */
    public DeezerRequest<Boolean> addPodcast(long userId, long podcastId) {
        return addPodcast(UserId.of(userId), podcastId);
    }

    private DeezerRequest<Boolean> addPodcast(UserId userId, long podcastId) {
        return createDeezerRequest(userId, podcastId, userService::addPodcast);
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
        return addRadio(UserId.ME, radioId);
    }

    /**
     * Creates a request to add a radio to a specific user's favorites.
     *
     * @param userId  the user ID
     * @param radioId the ID of the radio to add
     * @return a request that, when executed, will add the specified radio to the user's favorites
     */
    public DeezerRequest<Boolean> addRadio(long userId, long radioId) {
        return addRadio(UserId.of(userId), radioId);
    }

    private DeezerRequest<Boolean> addRadio(UserId userId, long radioId) {
        return createDeezerRequest(userId, radioId, userService::addRadio);
    }

    //endregion

    //region addTracks

    /**
     * Creates a request to add track(s) to the user's favorites.
     *
     * @param trackIds the IDs of the tracks to add
     * @return a request that, when executed, will add track(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addTracks(Long... trackIds) {
        return addTracks(Arrays.asList(trackIds));
    }

    /**
     * Creates a request to add track(s) to the user's favorites.
     *
     * @param trackIds the IDs of the tracks to add
     * @return a request that, when executed, will add track(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addTracks(Collection<Long> trackIds) {
        return addTracks(UserId.ME, trackIds);
    }

    /**
     * Creates a request to add track(s) to a specific user's favorites.
     *
     * @param userId   the user ID
     * @param trackIds the IDs of the tracks to add
     * @return a request that, when executed, will add track(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addTracks(long userId, Long... trackIds) {
        return addTracks(userId, Arrays.asList(trackIds));
    }

    /**
     * Creates a request to add track(s) to a specific user's favorites.
     *
     * @param userId   the user ID
     * @param trackIds the IDs of the tracks to add
     * @return a request that, when executed, will add track(s) to the user's favorites
     */
    public DeezerRequest<Boolean> addTracks(long userId, Collection<Long> trackIds) {
        return addTracks(UserId.of(userId), trackIds);
    }

    private DeezerRequest<Boolean> addTracks(UserId userId, Collection<Long> trackIds) {
        return createDeezerRequest(userId, trackIds, userService::addTracks);
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
        return createPlaylist(UserId.ME, title);
    }

    /**
     * Creates a request to create a new playlist for a specific user.
     *
     * @param userId the user ID
     * @param title  the title of the playlist to create
     * @return a request that, when executed, will create a new playlist for the user
     */
    public CreatePlaylistDeezerRequest createPlaylist(long userId, String title) {
        return createPlaylist(UserId.of(userId), title);
    }

    private CreatePlaylistDeezerRequest createPlaylist(UserId userId, String title) {
        return new CreatePlaylistDeezerRequest(userId.id(), accessTokenManager, title, userService);
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
        return followUser(UserId.ME, followeeId);
    }

    /**
     * Creates a request to follow another user.
     *
     * @param userId     the user ID
     * @param followeeId the ID of the user to follow
     * @return a request that, when executed, will follow the specified user
     */
    public DeezerRequest<Boolean> followUser(long userId, long followeeId) {
        return followUser(UserId.of(userId), followeeId);
    }

    private DeezerRequest<Boolean> followUser(UserId userId, long followeeId) {
        return createDeezerRequest(userId, followeeId, userService::followUser);
    }

    //endregion

    //region getAlbumChart

    /**
     * Creates a request to get the user's top albums.
     *
     * @return a request that, when executed, will return the user's top albums
     */
    public PagingDeezerRequest<Page<Album>> getAlbumChart() {
        return getAlbumChart(UserId.ME);
    }

    /**
     * Creates a request to get a user's top albums.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return the user's top albums
     */
    public PagingDeezerRequest<Page<Album>> getAlbumChart(long userId) {
        return getAlbumChart(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Album>> getAlbumChart(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getAlbumChart);
    }

    //endregion

    //region getAlbumRecommendations

    /**
     * Creates a request to return a list of album recommendations.
     *
     * @return a request that, when executed, will return a list of album recommendations
     */
    public PagingDeezerRequest<Page<Album>> getAlbumRecommendations() {
        return getAlbumRecommendations(UserId.ME);
    }

    /**
     * Creates a request to return a list of album recommendations.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of album recommendations
     */
    public PagingDeezerRequest<Page<Album>> getAlbumRecommendations(long userId) {
        return getAlbumRecommendations(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Album>> getAlbumRecommendations(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getAlbumRecommendations);
    }

    //endregion

    //region getArtistChart

    /**
     * Creates a request to return a list of the user's top artists.
     *
     * @return a request that, when executed, will return a list of the user's top artists
     */
    public PagingDeezerRequest<Page<Artist>> getArtistChart() {
        return getArtistChart(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's top artists.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's top artists
     */
    public PagingDeezerRequest<Page<Artist>> getArtistChart(long userId) {
        return getArtistChart(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Artist>> getArtistChart(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getArtistChart);
    }

    //endregion

    //region getArtistRecommendations

    /**
     * Creates a request to return a list of artists recommendations.
     *
     * @return a request that, when executed, will return a list of artists recommendations
     */
    public PagingDeezerRequest<Page<Artist>> getArtistRecommendations() {
        return getArtistRecommendations(UserId.ME);
    }

    /**
     * Creates a request to return a list of artists recommendations.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of artists recommendations
     */
    public PagingDeezerRequest<Page<Artist>> getArtistRecommendations(long userId) {
        return getArtistRecommendations(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Artist>> getArtistRecommendations(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getArtistRecommendations);
    }

    //endregion

    //region getArtists

    /**
     * Creates a request to return a list of the user's favorite artists.
     *
     * @return a request that, when executed, will return a list of the user's favorite artists
     */
    public PagingDeezerRequest<Page<Artist>> getArtists() {
        return getArtists(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's favorite artists.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's favorite artists
     */
    public PagingDeezerRequest<Page<Artist>> getArtists(long userId) {
        return getArtists(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Artist>> getArtists(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getArtists);
    }

    //endregion

    //region getChart

    /**
     * Creates a request to return a list of the user's top tracks.
     *
     * @return a request that, when executed, will return a list of the user's top tracks
     */
    public PagingDeezerRequest<Page<Track>> getChart() {
        return getChart(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's top tracks.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's top tracks
     */
    public PagingDeezerRequest<Page<Track>> getChart(long userId) {
        return getChart(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Track>> getChart(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getChart);
    }

    //endregion

    //region getFlow

    /**
     * Creates a request to return a list of user's flow tracks.
     *
     * @return a request that, when executed, will return a list of user's flow tracks
     */
    public DeezerRequest<Page<Track>> getFlow() {
        return getFlow(UserId.ME);
    }

    /**
     * Creates a request to return a list of user's flow tracks.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of user's flow tracks
     */
    public DeezerRequest<Page<Track>> getFlow(long userId) {
        return getFlow(UserId.of(userId));
    }

    private DeezerRequest<Page<Track>> getFlow(UserId userId) {
        return createDeezerRequest(userId, userService::getFlow);
    }

    //endregion

    //region getFollowers

    /**
     * Creates a request to return a list of the user's followers.
     *
     * @return a request that, when executed, will return a list of the user's followers
     */
    public PagingDeezerRequest<Page<User>> getFollowers() {
        return getFollowers(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's followers.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's followers
     */
    public PagingDeezerRequest<Page<User>> getFollowers(long userId) {
        return getFollowers(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<User>> getFollowers(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getFollowers);
    }

    //endregion

    //region getFollowings

    /**
     * Creates a request to return a list of users followed by the user.
     *
     * @return a request that, when executed, will return a list of users followed by the user
     */
    public PagingDeezerRequest<Page<User>> getFollowings() {
        return getFollowings(UserId.ME);
    }

    /**
     * Creates a request to return a list of users followed by the user.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of users followed by the user
     */
    public PagingDeezerRequest<Page<User>> getFollowings(long userId) {
        return getFollowings(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<User>> getFollowings(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getFollowings);
    }

    //endregion

    //region getHistory

    /**
     * Creates a request to return a list of the user's listening history.
     *
     * @return a request that, when executed, will return a list of the user's listening history
     */
    public PagingDeezerRequest<Page<Track>> getHistory() {
        return getHistory(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's listening history.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's listening history
     */
    public PagingDeezerRequest<Page<Track>> getHistory(long userId) {
        return getHistory(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Track>> getHistory(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getHistory);
    }

    //endregion

    //region getOptions

    /**
     * Creates a request to get the user's options.
     *
     * @return a request that, when executed, will return the user's options
     */
    public DeezerRequest<Options> getOptions() {
        return getOptions(UserId.ME);
    }

    /**
     * Creates a request to get the user's options.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return the user's options
     */
    public DeezerRequest<Options> getOptions(long userId) {
        return getOptions(UserId.of(userId));
    }

    private DeezerRequest<Options> getOptions(UserId userId) {
        return createDeezerRequest(userId, userService::getOptions);
    }

    //endregion

    //region getPermissions

    /**
     * Creates a request to get the user's permissions.
     *
     * @return a request that, when executed, will return the user's permissions
     */
    public DeezerRequest<PermissionsResponse> getPermissions() {
        return getPermissions(UserId.ME);
    }

    /**
     * Creates a request to get the user's permissions.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return the user's permissions
     */
    public DeezerRequest<PermissionsResponse> getPermissions(long userId) {
        return getPermissions(UserId.of(userId));
    }

    private DeezerRequest<PermissionsResponse> getPermissions(UserId userId) {
        return createDeezerRequest(userId, userService::getPermissions);
    }

    //endregion

    //region getPersonalSongs

    /**
     * Creates a request to return a list of the user's personal songs.
     *
     * @return a request that, when executed, will return a list of the user's personal songs
     */
    public PagingDeezerRequest<Page<Track>> getPersonalSongs() {
        return getPersonalSongs(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's personal songs.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's personal songs
     */
    public PagingDeezerRequest<Page<Track>> getPersonalSongs(long userId) {
        return getPersonalSongs(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Track>> getPersonalSongs(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getPersonalSongs);
    }

    //endregion

    //region getPlaylistChart

    /**
     * Creates a request to return a list of the user's top playlists.
     *
     * @return a request that, when executed, will return a list of the user's top playlists
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylistChart() {
        return getPlaylistChart(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's top playlists.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's top playlists
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylistChart(long userId) {
        return getPlaylistChart(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Playlist>> getPlaylistChart(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getPlaylistChart);
    }

    //endregion

    //region getPlaylistRecommendations

    /**
     * Creates a request to return a list of playlists recommendations.
     *
     * @return a request that, when executed, will return a list of playlists recommendations
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylistRecommendations() {
        return getPlaylistRecommendations(UserId.ME);
    }

    /**
     * Creates a request to return a list of playlists recommendations.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of playlists recommendations
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylistRecommendations(long userId) {
        return getPlaylistRecommendations(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Playlist>> getPlaylistRecommendations(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getPlaylistRecommendations);
    }

    //endregion

    //region getPlaylists

    /**
     * Creates a request to return a list of user's public playlists.
     *
     * @return a request that, when executed, will return a list of user's public playlists
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylists() {
        return getPlaylists(UserId.ME);
    }

    /**
     * Creates a request to return a list of user's public playlists.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of user's public playlists
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylists(long userId) {
        return getPlaylists(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Playlist>> getPlaylists(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getPlaylists);
    }

    //endregion

    //region getRadioRecommendations

    /**
     * Creates a request to return a list of radio recommendations.
     *
     * @return a request that, when executed, will return a list of radio recommendations
     */
    public PagingDeezerRequest<Page<Radio>> getRadioRecommendations() {
        return getRadioRecommendations(UserId.ME);
    }

    /**
     * Creates a request to return a list of radio recommendations.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of radio recommendations
     */
    public PagingDeezerRequest<Page<Radio>> getRadioRecommendations(long userId) {
        return getRadioRecommendations(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Radio>> getRadioRecommendations(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getRadioRecommendations);
    }

    //endregion

    //region getRadios

    /**
     * Creates a request to return a list of the user's favorite radios.
     *
     * @return a request that, when executed, will return a list of the user's favorite radios
     */
    public PagingDeezerRequest<Page<Radio>> getRadios() {
        return getRadios(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's favorite radios.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's favorite radios
     */
    public PagingDeezerRequest<Page<Radio>> getRadios(long userId) {
        return getRadios(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Radio>> getRadios(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getRadios);
    }

    //endregion

    //region getReleaseRecommendations

    /**
     * Creates a request to return a list of release recommendations.
     *
     * @return a request that, when executed, will return a list of release recommendations
     */
    public PagingDeezerRequest<Page<Album>> getReleaseRecommendations() {
        return getReleaseRecommendations(UserId.ME);
    }

    /**
     * Creates a request to return a list of release recommendations.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of release recommendations
     */
    public PagingDeezerRequest<Page<Album>> getReleaseRecommendations(long userId) {
        return getReleaseRecommendations(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Album>> getReleaseRecommendations(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getReleaseRecommendations);
    }

    //endregion

    //region getTrackChart

    /**
     * Creates a request to return a list of the user's top tracks.
     *
     * @return a request that, when executed, will return a list of the user's top tracks
     */
    public PagingDeezerRequest<Page<Track>> getTrackChart() {
        return getTrackChart(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's top tracks.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's top tracks
     */
    public PagingDeezerRequest<Page<Track>> getTrackChart(long userId) {
        return getTrackChart(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Track>> getTrackChart(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getTrackChart);
    }

    //endregion

    //region getTrackRecommendations

    /**
     * Creates a request to return a list of track recommendations.
     *
     * @return a request that, when executed, will return a list of track recommendations
     */
    public DeezerRequest<Page<Track>> getTrackRecommendations() {
        return getTrackRecommendations(UserId.ME);
    }

    /**
     * Creates a request to return a list of track recommendations.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of track recommendations
     */
    public DeezerRequest<Page<Track>> getTrackRecommendations(long userId) {
        return getTrackRecommendations(UserId.of(userId));
    }

    private DeezerRequest<Page<Track>> getTrackRecommendations(UserId userId) {
        return createDeezerRequest(userId, userService::getTrackRecommendations);
    }

    //endregion

    //region getTracks

    /**
     * Creates a request to return a list of user's favorite tracks.
     *
     * @return a request that, when executed, will return a list of user's favorite tracks
     */
    public PagingDeezerRequest<Page<Track>> getTracks() {
        return getTracks(UserId.ME);
    }

    /**
     * Creates a request to return a list of user's favorite tracks.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of user's favorite tracks
     */
    public PagingDeezerRequest<Page<Track>> getTracks(long userId) {
        return getTracks(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Track>> getTracks(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getTracks);
    }

    //endregion

    //region getUser

    /**
     * Creates a request to get the user information.
     *
     * @return a request that, when executed, will return the user information
     */
    public DeezerRequest<User> getUser() {
        return getUser(UserId.ME);
    }

    /**
     * Creates a request to get the user information.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return the user information
     */
    public DeezerRequest<User> getUser(long userId) {
        return getUser(UserId.of(userId));
    }

    private DeezerRequest<User> getUser(UserId userId) {
        return createDeezerRequest(userId, userService::getUser);
    }

    //endregion

    //region getUserAlbums

    /**
     * Creates a request to return a list of the user's favorite albums.
     *
     * @return a request that, when executed, will return a list of the user's favorite albums
     */
    public PagingDeezerRequest<Page<Album>> getAlbums() {
        return getAlbums(UserId.ME);
    }

    /**
     * Creates a request to return a list of the user's favorite albums.
     *
     * @param userId the user ID
     * @return a request that, when executed, will return a list of the user's favorite albums
     */
    public PagingDeezerRequest<Page<Album>> getAlbums(long userId) {
        return getAlbums(UserId.of(userId));
    }

    private PagingDeezerRequest<Page<Album>> getAlbums(UserId userId) {
        return createPagingDeezerRequest(userId, userService::getAlbums);
    }

    //endregion

    //region removeAlbum

    /**
     * Creates a request to remove an album from the user's library.
     *
     * @param albumId the ID of the album to remove
     * @return a request that, when executed, will remove an album from the user's library
     */
    public DeezerRequest<Boolean> removeAlbum(long albumId) {
        return removeAlbum(UserId.ME, albumId);
    }

    /**
     * Creates a request to remove an album from the user's library.
     *
     * @param userId  the user ID
     * @param albumId the ID of the album to remove
     * @return a request that, when executed, will remove an album from the user's library
     */
    public DeezerRequest<Boolean> removeAlbum(long userId, long albumId) {
        return removeAlbum(UserId.of(userId), albumId);
    }

    private DeezerRequest<Boolean> removeAlbum(UserId userId, long albumId) {
        return createDeezerRequest(userId, albumId, userService::removeAlbum);
    }

    //endregion

    //region removeArtist

    /**
     * Creates a request to remove an artist from the user's favorites.
     *
     * @param artistId the ID of the artist to remove
     * @return a request that, when executed, will remove an artist from the user's favorites
     */
    public DeezerRequest<Boolean> removeArtist(long artistId) {
        return removeArtist(UserId.ME, artistId);
    }

    /**
     * Creates a request to remove an artist from the user's favorites.
     *
     * @param userId   the user ID
     * @param artistId the ID of the artist to remove
     * @return a request that, when executed, will remove an artist from the user's favorites
     */
    public DeezerRequest<Boolean> removeArtist(long userId, long artistId) {
        return removeArtist(UserId.of(userId), artistId);
    }

    private DeezerRequest<Boolean> removeArtist(UserId userId, long artistId) {
        return createDeezerRequest(userId, artistId, userService::removeArtist);
    }

    //endregion

    //region removePlaylist

    /**
     * Creates a request to remove a playlist from the user's favorites.
     *
     * @param playlistId the ID of the playlist to remove
     * @return a request that, when executed, will remove a playlist from the user's favorites
     */
    public DeezerRequest<Boolean> removePlaylist(long playlistId) {
        return removePlaylist(UserId.ME, playlistId);
    }

    /**
     * Creates a request to remove a playlist from the user's favorites.
     *
     * @param userId     the user ID
     * @param playlistId the ID of the playlist to remove
     * @return a request that, when executed, will remove a playlist from the user's favorites
     */
    public DeezerRequest<Boolean> removePlaylist(long userId, long playlistId) {
        return removePlaylist(UserId.of(userId), playlistId);
    }

    private DeezerRequest<Boolean> removePlaylist(UserId userId, long playlistId) {
        return createDeezerRequest(userId, playlistId, userService::removePlaylist);
    }

    //endregion

    //region removePodcast

    /**
     * Creates a request to remove a podcast from the user's favorites.
     *
     * @param podcastId the ID of the podcast to remove
     * @return a request that, when executed, will remove a podcast from the user's favorites
     */
    public DeezerRequest<Boolean> removePodcast(long podcastId) {
        return removePodcast(UserId.ME, podcastId);
    }

    /**
     * Creates a request to remove a podcast from the user's favorites.
     *
     * @param userId    the user ID
     * @param podcastId the ID of the podcast to remove
     * @return a request that, when executed, will remove a podcast from the user's favorites
     */
    public DeezerRequest<Boolean> removePodcast(long userId, long podcastId) {
        return removePodcast(UserId.of(userId), podcastId);
    }

    private DeezerRequest<Boolean> removePodcast(UserId userId, long podcastId) {
        return createDeezerRequest(userId, podcastId, userService::removePodcast);
    }

    //endregion

    //region removeRadio

    /**
     * Creates a request to remove a radio from the user's favorites.
     *
     * @param radioId the ID of the radio to remove
     * @return a request that, when executed, will remove a radio from the user's favorites
     */
    public DeezerRequest<Boolean> removeRadio(long radioId) {
        return removeRadio(UserId.ME, radioId);
    }

    /**
     * Creates a request to remove a radio from the user's favorites.
     *
     * @param userId  the user ID
     * @param radioId the ID of the radio to remove
     * @return a request that, when executed, will remove a radio from the user's favorites
     */
    public DeezerRequest<Boolean> removeRadio(long userId, long radioId) {
        return removeRadio(UserId.of(userId), radioId);
    }

    private DeezerRequest<Boolean> removeRadio(UserId userId, long radioId) {
        return createDeezerRequest(userId, radioId, userService::removeRadio);
    }

    //endregion

    //region removeTrack

    /**
     * Creates a request to remove a track from the user's favorites.
     *
     * @param trackId the ID of the track to remove
     * @return a request that, when executed, will remove a track from the user's favorites
     */
    public DeezerRequest<Boolean> removeTrack(long trackId) {
        return removeTrack(UserId.ME, trackId);
    }

    /**
     * Creates a request to remove a track from the user's favorites.
     *
     * @param userId  the user ID
     * @param trackId the ID of the track to remove
     * @return a request that, when executed, will remove a track from the user's favorites
     */
    public DeezerRequest<Boolean> removeTrack(long userId, long trackId) {
        return removeTrack(UserId.of(userId), trackId);
    }

    private DeezerRequest<Boolean> removeTrack(UserId userId, long trackId) {
        return createDeezerRequest(userId, trackId, userService::removeTrack);
    }

    //endregion

    //region unfollowUser

    /**
     * Creates a request to unfollow another user.
     *
     * @param followeeId the ID of the user to unfollow
     * @return a request that, when executed, will unfollow the specified user
     */
    public DeezerRequest<Boolean> unfollowUser(long followeeId) {
        return unfollowUser(UserId.ME, followeeId);
    }

    /**
     * Creates a request to unfollow another user.
     *
     * @param userId     the user ID
     * @param followeeId the ID of the user to unfollow
     * @return a request that, when executed, will unfollow the specified user
     */
    public DeezerRequest<Boolean> unfollowUser(long userId, long followeeId) {
        return unfollowUser(UserId.of(userId), followeeId);
    }

    private DeezerRequest<Boolean> unfollowUser(UserId userId, long followeeId) {
        return createDeezerRequest(userId, followeeId, userService::unfollowUser);
    }

    //endregion

    private <T> DeezerRequest<T> createDeezerRequest(
            UserId userId,
            BiFunction<String, String, CompletableFuture<T>> asyncMethod
    ) {
        return new SimpleDeezerRequest<>(
                accessTokenManager,
                accessToken -> asyncMethod.apply(userId.id(), accessToken)
        );
    }

    private <T, R> DeezerRequest<R> createDeezerRequest(
            UserId userId,
            T argument,
            TriFunction<String, String, T, CompletableFuture<R>> asyncMethod
    ) {
        return createDeezerRequest(userId, (id, accessToken) -> asyncMethod.apply(id, accessToken, argument));
    }

    private <T> PagingDeezerRequest<Page<T>> createPagingDeezerRequest(
            UserId userId,
            QuadFunction<String, String, Integer, Integer, CompletableFuture<Page<T>>> asyncMethod
    ) {
        return new GetByIdPagingDeezerRequest<>(userId.id(), accessTokenManager, asyncMethod);
    }

    private record UserId(String id) {
        private static final UserId ME = new UserId("me");

        private static UserId of(long userId) {
            return new UserId(String.valueOf(userId));
        }
    }
}
