package io.github.yvasyliev.dz4j.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.dz4j.feign.AccessTokenExpander;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.BookmarkResponse;
import io.github.yvasyliev.dz4j.model.Episode;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Service for managing episodes on Deezer.
 */
@Headers("Accept: application/json")
public interface EpisodeService {
    /**
     * Bookmarks an episode.
     *
     * @param episodeId   episode ID
     * @param accessToken Deezer access token
     * @param offset      the offset in seconds of the episode to bookmark
     * @return the result of the bookmark operation
     */
    @RequestLine("POST /episode/{episodeId}/bookmark")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    CompletableFuture<BookmarkResponse> bookmarkEpisode(
            @Param("episodeId") long episodeId,
            @Param(value = "access_token", expander = AccessTokenExpander.class) @Nullable AccessToken accessToken,
            @Param("offset") int offset
    );

    /**
     * Returns an episode.
     *
     * @param episodeId   episode ID
     * @param accessToken Deezer access token
     * @return an episode
     */
    @RequestLine("GET /episode/{episodeId}?access_token={accessToken}")
    CompletableFuture<Episode> getEpisode(
            @Param("episodeId") long episodeId,
            @Param(value = "accessToken", expander = AccessTokenExpander.class) @Nullable AccessToken accessToken
    );

    /**
     * Unbookmarks an episode.
     *
     * @param episodeId   episode ID
     * @param accessToken Deezer access token
     * @return the result of the unbookmark operation
     */
    @RequestLine("DELETE /episode/{episodeId}/bookmark?access_token={accessToken}")
    CompletableFuture<BookmarkResponse> unbookmarkEpisode(
            @Param("episodeId") long episodeId,
            @Param(value = "accessToken", expander = AccessTokenExpander.class) @Nullable AccessToken accessToken
    );
}
