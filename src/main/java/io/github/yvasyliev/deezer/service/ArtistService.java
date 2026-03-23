package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;

import java.util.concurrent.CompletableFuture;

/**
 * Service for interacting with artists on Deezer.
 */
@Headers("Accept: application/json")
public interface ArtistService {
    /**
     * Returns a list of artist's albums.
     *
     * @param artistId artist ID
     * @param index    the offset of the first object
     * @param limit    the maximum number of objects to return
     * @return list of artist's albums
     */
    @RequestLine("GET /artist/{artistId}/albums?index={index}&limit={limit}")
    CompletableFuture<Page<Album>> getAlbums(
            @Param("artistId") long artistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns an artist.
     *
     * @param artistId artist ID
     * @return artist
     */
    @RequestLine("GET /artist/{artistId}")
    CompletableFuture<Artist> getArtist(@Param("artistId") long artistId);

    /**
     * Returns a list of artist's fans.
     *
     * @param artistId artist ID
     * @param index    the offset of the first object
     * @param limit    the maximum number of objects to return
     * @return list of artist's fans
     */
    @RequestLine("GET /artist/{artistId}/fans?index={index}&limit={limit}")
    CompletableFuture<Page<User>> getFans(
            @Param("artistId") long artistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of artist's playlists.
     *
     * @param artistId artist ID
     * @param index    the offset of the first object
     * @param limit    the maximum number of objects to return
     * @return list of artist's playlists
     */
    @RequestLine("GET /artist/{artistId}/playlists?index={index}&limit={limit}")
    CompletableFuture<Page<Playlist>> getPlaylists(
            @Param("artistId") long artistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of tracks.
     *
     * @param artistId artist ID
     * @param index    the offset of the first object
     * @param limit    the maximum number of objects to return
     * @return list of tracks
     */
    @RequestLine("GET /artist/{artistId}/radio?index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getRadio(
            @Param("artistId") long artistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of related artists.
     *
     * @param artistId artist ID
     * @param index    the offset of the first object
     * @param limit    the maximum number of objects to return
     * @return list of related artists
     */
    @RequestLine("GET /artist/{artistId}/related?index={index}&limit={limit}")
    CompletableFuture<Page<Artist>> getRelated(
            @Param("artistId") long artistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of top tracks.
     *
     * @param artistId artist ID
     * @param index    the offset of the first object
     * @param limit    the maximum number of objects to return
     * @return list of top tracks
     */
    @RequestLine("GET /artist/{artistId}/top?index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getTop(
            @Param("artistId") long artistId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );
}
