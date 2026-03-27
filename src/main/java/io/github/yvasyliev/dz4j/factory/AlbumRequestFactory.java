package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Album;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.model.User;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.IdDeezerRequest;
import io.github.yvasyliev.dz4j.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.dz4j.request.PagingDeezerRequest;
import io.github.yvasyliev.dz4j.service.AlbumService;
import io.github.yvasyliev.dz4j.util.TriFunction;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * Factory for creating requests related to albums.
 */
@RequiredArgsConstructor
public class AlbumRequestFactory {
    private final AlbumService albumService;

    /**
     * Creates a request to get an album.
     *
     * @param albumId album ID
     * @return request to get an album
     */
    public DeezerRequest<Album> getAlbum(long albumId) {
        return new IdDeezerRequest<>(albumId, albumService::getAlbum);
    }

    /**
     * Creates a request to get a list of album's fans.
     *
     * @param albumId album ID
     * @return request to get a list of album's fans
     */
    public PagingDeezerRequest<Page<User>> getFans(long albumId) {
        return createPagingDeezerRequest(albumId, albumService::getAlbumFans);
    }

    /**
     * Creates a request to get a list of album's tracks.
     *
     * @param albumId album ID
     * @return request to get a list of album's tracks
     */
    public PagingDeezerRequest<Page<Track>> getTracks(long albumId) {
        return createPagingDeezerRequest(albumId, albumService::getAlbumTracks);
    }

    private <T> PagingDeezerRequest<Page<T>> createPagingDeezerRequest(
            long albumId,
            TriFunction<Long, Integer, Integer, CompletableFuture<Page<T>>> asyncMethod
    ) {
        return new GetByIdPagingDeezerRequest<>(albumId, asyncMethod);
    }
}
