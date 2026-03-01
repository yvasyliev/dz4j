package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.BookmarkResponse;
import io.github.yvasyliev.deezer.model.Episode;

import java.util.concurrent.CompletableFuture;

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
            @Param("access_token") String accessToken,
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
            @Param("accessToken") String accessToken
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
            @Param("accessToken") String accessToken
    );
}
