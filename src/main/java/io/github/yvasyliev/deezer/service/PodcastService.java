package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.feign.AccessTokenExpander;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Episode;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Podcast;

import java.util.concurrent.CompletableFuture;

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
            @Param(value = "accessToken", expander = AccessTokenExpander.class) AccessToken accessToken
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
            @Param(value = "accessToken", expander = AccessTokenExpander.class) AccessToken accessToken
    );
}
