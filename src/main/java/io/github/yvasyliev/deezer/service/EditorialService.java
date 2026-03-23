package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Chart;
import io.github.yvasyliev.deezer.model.Editorial;
import io.github.yvasyliev.deezer.model.Page;

import java.util.concurrent.CompletableFuture;

/**
 * Service for accessing editorials on Deezer.
 */
@Headers("Accept: application/json")
public interface EditorialService {
    /**
     * Returns a list of editorials for the current country.
     *
     * @param index the offset of the first object
     * @param limit the maximum number of objects to return
     * @return a list of editorials for the current country
     */
    @RequestLine("GET /editorial?index={index}&limit={limit}")
    CompletableFuture<Page<Editorial>> getEditorials(@Param("index") Integer index, @Param("limit") Integer limit);

    /**
     * Returns an editorial for a specific genre.
     *
     * @param genreId the genre ID
     * @return an editorial for a specific genre
     */
    @RequestLine("GET /editorial/{genreId}")
    CompletableFuture<Editorial> getEditorial(@Param("genreId") long genreId);

    /**
     * Returns a list of albums selected every week by the Deezer Team.
     *
     * @param genreId the genre ID
     * @return a list of albums selected every week by the Deezer Team
     */
    @RequestLine("GET /editorial/{genreId}/selection")
    CompletableFuture<Page<Album>> getEditorialSelection(@Param("genreId") long genreId);

    /**
     * Returns the new releases per genre for the current country
     *
     * @param genreId the genre ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return the new releases per genre for the current country
     */
    @RequestLine("GET /editorial/{genreId}/releases?index={index}&limit={limit}")
    CompletableFuture<Page<Album>> getEditorialReleases(
            @Param("genreId") long genreId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Returns the top tracks, albums, artists, playlists and podcasts per genre for the current country.
     *
     * @param genreId the genre ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return the top tracks, albums, artists, playlists and podcasts per genre for the current country
     */
    @RequestLine("GET /editorial/{genreId}/charts?index={index}&limit={limit}")
    CompletableFuture<Page<Chart>> getEditorialCharts(
            @Param("genreId") long genreId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );
}
