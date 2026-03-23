package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;

import java.util.concurrent.CompletableFuture;

/**
 * Service for interacting with albums on Deezer.
 */
@Headers("Accept: application/json")
public interface AlbumService {
    /**
     * Returns an album.
     *
     * @param albumId album ID
     * @return album
     */
    @RequestLine("GET /album/{albumId}")
    CompletableFuture<Album> getAlbum(@Param("albumId") long albumId);

    /**
     * Returns a list of album's fans.
     *
     * @param albumId album ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return list of album's fans
     */
    @RequestLine("GET /album/{albumId}/fans?index={index}&limit={limit}")
    CompletableFuture<Page<User>> getAlbumFans(
            @Param("albumId") long albumId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of album's tracks.
     *
     * @param albumId album ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return list of album's tracks
     */
    @RequestLine("GET /album/{albumId}/tracks?index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getAlbumTracks(
            @Param("albumId") long albumId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );
}
