package io.github.yvasyliev.deezer.service;

import api.deezer.objects.GetPermissionsResponse;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserService extends DeezerService {
    String USER = "/user/{userId}";
    String USER_ALBUMS = "/user/{userId}/albums";
    String USER_ARTISTS = "/user/{userId}/artist";
    String USER_CHART = "/user/{userId}/charts";
    String USER_ALBUM_CHART = "/user/{userId}/charts/albums";
    String USER_ARTIST_CHART = "/user/{userId}/charts/artists";
    String USER_PLAYLIST_CHART = "/user/{userId}/charts/playlists";
    String USER_TRACK_CHART = "/user/{userId}/charts/tracks";
    String USER_FLOW = "/user/{userId}/flow";
    String USER_FOLLOWINGS = "/user/{userId}/followings";
    String USER_FOLLOWERS = "/user/{userId}/followers";
    String USER_HISTORY = "/user/{userId}/history";
    String USER_NOTIFICATIONS = "/user/{userId}/notifications";
    String USER_PERMISSIONS = "/user/{userId}/permissions";

    @RequestLine(GET + USER)
    User getUser(@Param("userId") long userId);

    @RequestLine(GET + USER)
    CompletableFuture<User> getUserAsync(@Param("userId") long userId);

    @RequestLine(GET + USER_ALBUMS)
    Page<Album, PagingMethod<Album>> getUserAlbums(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ALBUMS)
    CompletableFuture<Page<Album, PagingMethod<Album>>> getUserAlbumsAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ARTISTS)
    Page<Artist, PagingMethod<Artist>> getUserArtists(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ARTISTS)
    CompletableFuture<Page<Artist, PagingMethod<Artist>>> getUserArtistsAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_CHART)
    Page<Track, PagingMethod<Track>> getUserCharts(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_CHART)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getUserChartsAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ALBUM_CHART)
    Page<Album, PagingMethod<Album>> getUserAlbumChart(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ALBUM_CHART)
    CompletableFuture<Page<Album, PagingMethod<Album>>> getUserAlbumChartAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ARTIST_CHART)
    Page<Artist, PagingMethod<Artist>> getUserArtistChart(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_PLAYLIST_CHART)
    Page<Playlist, PagingMethod<Playlist>> getUserPlaylistChart(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_PLAYLIST_CHART)
    CompletableFuture<Page<Playlist, PagingMethod<Playlist>>> getUserPlaylistChartAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_TRACK_CHART)
    Page<Track, PagingMethod<Track>> getUserTrackChart(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_TRACK_CHART)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getUserTracksChartAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FLOW)
    Page<Track, PagingMethod<Track>> getUserFlow(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FLOW)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getUserFlowAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FOLLOWINGS)
    Page<User, PagingMethod<User>> getUserFollowings(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FOLLOWINGS)
    CompletableFuture<Page<User, PagingMethod<User>>> getUserFollowingsAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FOLLOWERS)
    Page<User, PagingMethod<User>> getUserFollowers(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FOLLOWERS)
    CompletableFuture<Page<User, PagingMethod<User>>> getUserFollowersAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_HISTORY)
    Page<Track, PagingMethod<Track>> getUserHistory(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_HISTORY)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getUserHistoryAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(POST + USER_NOTIFICATIONS)
    Boolean sendUserNotification(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(POST + USER_NOTIFICATIONS)
    CompletableFuture<Boolean> sendUserNotificationAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_PERMISSIONS)
    GetPermissionsResponse getUserPermissions(@Param("userId") long userId);

    @RequestLine(GET + USER_PERMISSIONS)
    CompletableFuture<GetPermissionsResponse> getUserPermissionsAsync(@Param("userId") long userId);
}
