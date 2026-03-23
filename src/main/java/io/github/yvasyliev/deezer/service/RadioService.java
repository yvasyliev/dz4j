package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.Genre;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Radio;
import io.github.yvasyliev.deezer.model.Track;

import java.util.concurrent.CompletableFuture;

/**
 * Service for interacting with Deezer's radio-related endpoints.
 */
@Headers("Accept: application/json")
public interface RadioService {
    /**
     * Returns a list of radio splitted by genre.
     *
     * @return a list of radio splitted by genre
     */
    @RequestLine("GET /radio/genres")
    CompletableFuture<Page<Genre>> getGenres();

    /**
     * Returns a list of personal radio splitted by genre (as MIX in website)
     *
     * @param index the offset of the first object
     * @param limit the maximum number of objects to return
     * @return a list of personal radio splitted by genre (as MIX in website)
     */
    @RequestLine("GET /radio/lists?index={index}&limit={limit}")
    CompletableFuture<Page<Radio>> getLists(@Param("index") Integer index, @Param("limit") Integer limit);

    /**
     * Returns a radio.
     *
     * @param radioId the radio ID
     * @return a radio
     */
    @RequestLine("GET /radio/{radioId}")
    CompletableFuture<Radio> getRadio(@Param("radioId") long radioId);

    /**
     * Returns a list of radios.
     *
     * @return a list of radios
     */
    @RequestLine("GET /radio")
    CompletableFuture<Page<Radio>> getRadios();

    /**
     * Get first 40 tracks in the radio.
     *
     * @param radioId the radio ID
     * @param index   the offset of the first object
     * @param limit   the maximum number of objects to return
     * @return a list of tracks in the radio
     */
    @RequestLine("GET /radio/{radioId}/tracks?index={index}&limit={limit}")
    CompletableFuture<Page<Track>> getTracks(
            @Param("radioId") long radioId,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );
}
