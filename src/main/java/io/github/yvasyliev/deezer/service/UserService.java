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
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
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
    CompletableFuture<User> getUserAsync(@Param("userId") long userId);

    @RequestLine(GET + USER_ALBUMS)
    CompletableFuture<Page<Album, DzPagingMethod<Album>>> getUserAlbumsAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ARTISTS)
    CompletableFuture<Page<Artist, DzPagingMethod<Artist>>> getUserArtistsAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_CHART)
    CompletableFuture<Page<Track, DzPagingMethod<Track>>> getUserChartsAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ALBUM_CHART)
    CompletableFuture<Page<Album, DzPagingMethod<Album>>> getUserAlbumChartAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_ARTIST_CHART)
    CompletableFuture<Page<Artist, DzPagingMethod<Artist>>> getUserArtistChartAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_PLAYLIST_CHART)
    CompletableFuture<Page<Playlist, DzPagingMethod<Playlist>>> getUserPlaylistChartAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_TRACK_CHART)
    CompletableFuture<Page<Track, DzPagingMethod<Track>>> getUserTracksChartAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FLOW)
    CompletableFuture<Page<Track, DzPagingMethod<Track>>> getUserFlowAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FOLLOWINGS)
    CompletableFuture<Page<User, DzPagingMethod<User>>> getUserFollowingsAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_FOLLOWERS)
    CompletableFuture<Page<User, DzPagingMethod<User>>> getUserFollowersAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_HISTORY)
    CompletableFuture<Page<Track, DzPagingMethod<Track>>> getUserHistoryAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(POST + USER_NOTIFICATIONS)
    CompletableFuture<Boolean> sendUserNotificationAsync(@Param("userId") long userId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + USER_PERMISSIONS)
    CompletableFuture<GetPermissionsResponse> getUserPermissionsAsync(@Param("userId") long userId);
}
