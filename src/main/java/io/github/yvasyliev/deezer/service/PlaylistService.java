package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface PlaylistService extends DeezerService {
    String PLAYLIST = "/playlist/{playlistId}";
    String PLAYLIST_FANS = "/playlist/{playlistId}/fans";
    String PLAYLIST_RADIO = "/playlist/{playlistId}/radio";
    String PLAYLIST_TRACKS = "/playlist/{playlistId}/tracks";

    @RequestLine(GET + PLAYLIST)
    Playlist getPlaylist(@Param("playlistId") long playlistId);

    @RequestLine(GET + PLAYLIST)
    CompletableFuture<Playlist> getPlaylistAsync(@Param("playlistId") long playlistId);

    @RequestLine(GET + PLAYLIST_FANS)
    Page<User, PagingMethod<User>> getPlaylistFans(@Param("playlistId") long playlistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + PLAYLIST_FANS)
    CompletableFuture<Page<User, PagingMethod<User>>> getPlaylistFansAsync(@Param("playlistId") long playlistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + PLAYLIST_RADIO)
    Page<Track, PagingMethod<Track>> getPlaylistRadio(@Param("playlistId") long playlistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + PLAYLIST_RADIO)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getPlaylistRadioAsync(@Param("playlistId") long playlistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + PLAYLIST_TRACKS)
    Page<Track, PagingMethod<Track>> getPlaylistTracks(@Param("playlistId") long playlistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + PLAYLIST_TRACKS)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getPlaylistTracksAsync(@Param("playlistId") long playlistId, @QueryMap Map<String, Object> queryParams);
}
