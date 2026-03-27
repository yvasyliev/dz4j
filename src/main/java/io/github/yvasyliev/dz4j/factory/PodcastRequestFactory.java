package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.TokenManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Episode;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Podcast;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.IdDeezerRequest;
import io.github.yvasyliev.dz4j.service.PodcastService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * Factory for creating requests related to podcasts.
 */
@RequiredArgsConstructor
public class PodcastRequestFactory {
    private final PodcastService podcastService;
    private final TokenManager<AccessToken> accessTokenManager;

    /**
     * Creates a request to get a list of podcast's episodes.
     *
     * @param podcastId podcast ID
     * @return request to get a list of podcast's episodes
     */
    public DeezerRequest<Page<Episode>> getEpisodes(long podcastId) {
        return createDeezerRequest(podcastId, podcastService::getEpisodes);
    }

    /**
     * Creates a request to get a podcast.
     *
     * @param podcastId podcast ID
     * @return request to get a podcast
     */
    public DeezerRequest<Podcast> getPodcast(long podcastId) {
        return createDeezerRequest(podcastId, podcastService::getPodcast);
    }

    private <T> DeezerRequest<T> createDeezerRequest(
            long podcastId,
            BiFunction<Long, String, CompletableFuture<T>> asyncMethod
    ) {
        return new IdDeezerRequest<>(podcastId, accessTokenManager, asyncMethod);
    }
}
