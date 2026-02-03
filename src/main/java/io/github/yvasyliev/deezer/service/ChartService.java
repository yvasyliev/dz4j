package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Chart;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Podcast;
import io.github.yvasyliev.deezer.model.Track;

import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface ChartService {
    /**
     * Returns a list of top albums in the specified genre.
     *
     * @param genreId genre ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return list of top albums in the specified genre
     */
    @RequestLine("GET /chart/{genreId}/albums?index={index}&limit={limit}")
    CompletableFuture<Page<Album>> getAlbumsChart(
            @Param("genreId") long genreId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of top artists in the specified genre.
     *
     * @param genreId genre ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return list of top artists in the specified genre
     */
    @RequestLine("GET /chart/{genreId}/artists?index={index}&limit={limit}")
    CompletableFuture<Page<Artist>> getArtistsChart(
            @Param("genreId") long genreId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns the overall chart for the specified genre.
     *
     * @param genreId genre ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return overall chart for the specified genre
     */
    @RequestLine("GET /chart/{genreId}?index={index}&limit={limit}")
    CompletableFuture<Chart> getChart(
            @Param("genreId") long genreId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of top playlists in the specified genre.
     *
     * @param genreId genre ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return list of top playlists in the specified genre
     */
    @RequestLine("GET /chart/{genreId}/playlists?index={index}&limit={limit}")
    CompletableFuture<Page<Playlist>> getPlaylistsChart(
            @Param("genreId") long genreId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of top podcasts in the specified genre.
     *
     * @param genreId genre ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return list of top podcasts in the specified genre
     */
    @RequestLine("GET /chart/{genreId}/podcasts?index={index}&limit={limit}")
    CompletableFuture<Page<Podcast>> getPodcastsChart(
            @Param("genreId") long genreId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns a list of top tracks in the specified genre.
     *
     * @param genreId genre ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return list of top tracks in the specified genre
     */
    @RequestLine("GET /chart/{genreId}/tracks?index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getTracksChart(
            @Param("genreId") long genreId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );
}
