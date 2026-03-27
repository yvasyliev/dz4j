package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.TokenManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Playlist;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.model.User;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.dz4j.request.PagingDeezerRequest;
import io.github.yvasyliev.dz4j.request.SimpleDeezerRequest;
import io.github.yvasyliev.dz4j.request.UpdatePlaylistDeezerRequest;
import io.github.yvasyliev.dz4j.service.PlaylistService;
import io.github.yvasyliev.dz4j.util.TriFunction;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * Factory for creating requests related to playlists.
 */
@RequiredArgsConstructor
public class PlaylistRequestFactory {
    private final PlaylistService playlistService;
    private final TokenManager<AccessToken> accessTokenManager;

    /**
     * Creates a request to add tracks to the playlist.
     *
     * @param playlistId playlist ID
     * @param trackIds   track IDs to add
     * @return a request that, when executed, will add the tracks to the playlist
     */
    public DeezerRequest<Boolean> addTracks(long playlistId, Long... trackIds) {
        return addTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Creates a request to add tracks to the playlist.
     *
     * @param playlistId playlist ID
     * @param trackIds   track IDs to add
     * @return a request that, when executed, will add the tracks to the playlist
     */
    public DeezerRequest<Boolean> addTracks(long playlistId, Collection<Long> trackIds) {
        return createDeezerRequest(playlistId, trackIds, playlistService::addTracks);
    }

    /**
     * Creates a request to delete the playlist.
     *
     * @param playlistId playlist ID
     * @return a request that, when executed, will delete the playlist
     */
    public DeezerRequest<Boolean> deletePlaylist(long playlistId) {
        return createDeezerRequest(playlistId, playlistService::deletePlaylist);
    }

    /**
     * Creates a request to delete tracks from the playlist.
     *
     * @param playlistId playlist ID
     * @param trackIds   track IDs to delete
     * @return a request that, when executed, will delete the tracks from the playlist
     */
    public DeezerRequest<Boolean> deleteTracks(long playlistId, Long... trackIds) {
        return deleteTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Creates a request to delete tracks from the playlist.
     *
     * @param playlistId playlist ID
     * @param trackIds   track IDs to delete
     * @return a request that, when executed, will delete the tracks from the playlist
     */
    public DeezerRequest<Boolean> deleteTracks(long playlistId, Collection<Long> trackIds) {
        return createDeezerRequest(playlistId, trackIds, playlistService::deleteTracks);
    }

    /**
     * Creates a request to get the playlist's fans.
     *
     * @param playlistId playlist ID
     * @return a request that, when executed, will return a list of the playlist's fans
     */
    public PagingDeezerRequest<Page<User>> getFans(long playlistId) {
        return createPagingDeezerRequest(playlistId, playlistService::getFans);
    }

    /**
     * Creates a request to get the playlist.
     *
     * @param playlistId playlist ID
     * @return a request that, when executed, will return the playlist
     */
    public DeezerRequest<Playlist> getPlaylist(long playlistId) {
        return new SimpleDeezerRequest<>(() -> playlistService.getPlaylist(playlistId));
    }

    /**
     * Creates a request to get a list of playlist's recommendation tracks.
     *
     * @param playlistId playlist ID
     * @return a request that, when executed, will return a list of the playlist's recommendation tracks.
     */
    public PagingDeezerRequest<Optional<Page<Track>>> getRadio(long playlistId) {
        return createPagingDeezerRequest(playlistId, playlistService::getRadio);
    }

    /**
     * Creates a request to get the playlist's tracks.
     *
     * @param playlistId playlist ID
     * @return a request that, when executed, will return a list of the playlist's tracks
     */
    public PagingDeezerRequest<Page<Track>> getTracks(long playlistId) {
        return createPagingDeezerRequest(playlistId, playlistService::getTracks);
    }

    /**
     * Creates a request to mark the playlist as seen.
     *
     * @param playlistId playlist ID
     * @return a request that, when executed, will mark the playlist as seen
     */
    public DeezerRequest<Boolean> markAsSeen(long playlistId) {
        return createDeezerRequest(playlistId, playlistService::markAsSeen);
    }

    /**
     * Creates a request to order the playlist's tracks.
     *
     * @param playlistId playlist ID
     * @param trackIds   track IDs in the desired order
     * @return a request that, when executed, will order the playlist's tracks
     */
    public DeezerRequest<Boolean> orderTracks(long playlistId, Long... trackIds) {
        return orderTracks(playlistId, Arrays.asList(trackIds));
    }

    /**
     * Creates a request to order the playlist's tracks.
     *
     * @param playlistId playlist ID
     * @param trackIds   track IDs in the desired order
     * @return a request that, when executed, will order the playlist's tracks
     */
    public DeezerRequest<Boolean> orderTracks(long playlistId, Collection<Long> trackIds) {
        return createDeezerRequest(playlistId, trackIds, playlistService::orderTracks);
    }

    /**
     * Creates a request to update the playlist.
     *
     * @param playlistId playlist ID
     * @return a request that, when executed, will update the playlist
     */
    public DeezerRequest<Boolean> updatePlaylist(long playlistId) {
        return new UpdatePlaylistDeezerRequest(playlistId, accessTokenManager, playlistService);
    }

    private <T> PagingDeezerRequest<T> createPagingDeezerRequest(
            long playlistId,
            TriFunction<Long, Integer, Integer, CompletableFuture<T>> asyncMethod
    ) {
        return new GetByIdPagingDeezerRequest<>(playlistId, asyncMethod);
    }

    private DeezerRequest<Boolean> createDeezerRequest(
            long playlistId,
            BiFunction<Long, String, CompletableFuture<Boolean>> asyncMethod
    ) {
        return new SimpleDeezerRequest<>(accessTokenManager, accessToken -> asyncMethod.apply(playlistId, accessToken));
    }

    private DeezerRequest<Boolean> createDeezerRequest(
            long playlistId,
            Collection<Long> trackIds,
            TriFunction<Long, String, Collection<Long>, CompletableFuture<Boolean>> asyncMethod
    ) {
        return createDeezerRequest(
                playlistId,
                (id, accessToken) -> asyncMethod.apply(id, accessToken, trackIds)
        );
    }
}
