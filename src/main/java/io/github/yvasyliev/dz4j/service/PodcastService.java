package io.github.yvasyliev.dz4j.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.dz4j.feign.AccessTokenExpander;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Episode;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Podcast;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Service for interacting with podcasts on Deezer.
 */
@Headers("Accept: application/json")
public interface PodcastService {
    /**
     * Returns a list of episodes for a podcast.
     *
     * @param podcastId   the podcast ID
     * @param accessToken Deezer access token
     * @return a list of episodes for a podcast
     */
    @RequestLine("GET /podcast/{podcastId}/episodes?access_token={accessToken}")
    CompletableFuture<Page<Episode>> getEpisodes(
            @Param("podcastId") long podcastId,
            @Param(value = "accessToken", expander = AccessTokenExpander.class) @Nullable AccessToken accessToken
    );

    /**
     * Returns a podcast.
     *
     * @param podcastId   the podcast ID
     * @param accessToken Deezer access token
     * @return a podcast
     */
    @RequestLine("GET /podcast/{podcastId}?access_token={accessToken}")
    CompletableFuture<Podcast> getPodcast(
            @Param("podcastId") long podcastId,
            @Param(value = "accessToken", expander = AccessTokenExpander.class) @Nullable AccessToken accessToken
    );
}
