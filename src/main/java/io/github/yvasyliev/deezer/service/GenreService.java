package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Genre;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Radio;

import java.util.concurrent.CompletableFuture;

/**
 * Service for interacting with genres on Deezer.
 */
@Headers("Accept: application/json")
public interface GenreService {
    /**
     * Returns all artists for a genre.
     *
     * @param genreId the genre ID
     * @return all artists for a genre
     */
    @RequestLine("GET /genre/{genreId}/artists")
    CompletableFuture<Page<Artist>> getArtists(@Param("genreId") long genreId);

    /**
     * Returns a genre.
     *
     * @param genreId the genre ID
     * @return a genre
     */
    @RequestLine("GET /genre/{genreId}")
    CompletableFuture<Genre> getGenre(@Param("genreId") long genreId);

    /**
     * Returns all genres.
     *
     * @return all genres
     */
    @RequestLine("GET /genre")
    CompletableFuture<Page<Genre>> getGenres();

    /**
     * Returns all radios for a genre.
     *
     * @param genreId the genre ID
     * @return all radios for a genre
     */
    @RequestLine("GET /genre/{genreId}/radios")
    CompletableFuture<Page<Radio>> getRadios(@Param("genreId") long genreId);
}
