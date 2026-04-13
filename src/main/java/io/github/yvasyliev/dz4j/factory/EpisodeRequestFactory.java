package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.BookmarkResponse;
import io.github.yvasyliev.dz4j.model.Episode;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.IdDeezerRequest;
import io.github.yvasyliev.dz4j.service.EpisodeService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * Factory for creating requests related to episodes.
 */
@RequiredArgsConstructor
public class EpisodeRequestFactory {
    private final EpisodeService episodeService;
    private final AuthorizationManager authorizationManager;

    /**
     * Creates a request to bookmark an episode.
     *
     * @param episodeId the episode ID
     * @param offset    the offset in seconds to start the episode from
     * @return a request to bookmark the episode
     */
    public DeezerRequest<BookmarkResponse> bookmarkEpisode(long episodeId, int offset) {
        return createDeezerRequest(episodeId, (id, token) -> episodeService.bookmarkEpisode(id, token, offset));
    }

    /**
     * Creates a request to get an episode.
     *
     * @param episodeId the episode ID
     * @return a request to get the episode
     */
    public DeezerRequest<Episode> getEpisode(long episodeId) {
        return createDeezerRequest(episodeId, episodeService::getEpisode);
    }

    /**
     * Creates a request to unbookmark an episode.
     *
     * @param episodeId the episode ID
     * @return a request to unbookmark the episode
     */
    public DeezerRequest<BookmarkResponse> unbookmarkEpisode(long episodeId) {
        return createDeezerRequest(episodeId, episodeService::unbookmarkEpisode);
    }

    private <T> DeezerRequest<T> createDeezerRequest(
            long episodeId,
            BiFunction<Long, AccessToken, CompletableFuture<T>> asyncMethod
    ) {
        return new IdDeezerRequest<>(episodeId, authorizationManager, asyncMethod);
    }
}
