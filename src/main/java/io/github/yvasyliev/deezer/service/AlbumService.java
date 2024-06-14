package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface AlbumService extends DeezerService {
    String ALBUM = "/album/{albumId}";
    String ALBUM_FANS = "/album/{albumId}/fans";
    String ALBUM_TRACKS = "/album/{albumId}/tracks";

    @RequestLine(GET + ALBUM)
    Album getAlbum(@Param("albumId") long albumId);

    @RequestLine(GET + ALBUM)
    CompletableFuture<Album> getAlbumAsync(@Param("albumId") long albumId);

    @RequestLine(GET + ALBUM_FANS)
    Page<User, PagingMethod<User>> getAlbumFans(@Param("albumId") long albumId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ALBUM_FANS)
    CompletableFuture<Page<User, PagingMethod<User>>> getAlbumFansAsync(@Param("albumId") long albumId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ALBUM_TRACKS)
    Page<Track, PagingMethod<Track>> getAlbumTracks(@Param("albumId") long albumId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ALBUM_TRACKS)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getAlbumTracksAsync(@Param("albumId") long albumId, @QueryMap Map<String, Object> queryParams);
}
