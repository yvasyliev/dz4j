package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.BookmarkResponse;
import io.github.yvasyliev.deezer.model.Episode;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.IdDeezerRequest;
import io.github.yvasyliev.deezer.service.EpisodeService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class EpisodeRequestFactory {
    private final EpisodeService episodeService;
    private final TokenManager<AccessToken> accessTokenManager;

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
            BiFunction<Long, String, CompletableFuture<T>> asyncMethod
    ) {
        return new IdDeezerRequest<>(episodeId, accessTokenManager, asyncMethod);
    }
}
