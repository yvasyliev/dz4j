package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdDeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.deezer.request.PagingDeezerRequest;
import io.github.yvasyliev.deezer.service.AlbumService;
import lombok.RequiredArgsConstructor;

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
        return new GetByIdDeezerRequest<>(albumId, albumService::getAlbum);
    }

    /**
     * Creates a request to get a list of album's fans.
     *
     * @param albumId album ID
     * @return request to get a list of album's fans
     */
    public PagingDeezerRequest<Page<User>> getAlbumFans(long albumId) {
        return new GetByIdPagingDeezerRequest<>(albumId, albumService::getAlbumFans);
    }

    /**
     * Creates a request to get a list of album's tracks.
     *
     * @param albumId album ID
     * @return request to get a list of album's tracks
     */
    public PagingDeezerRequest<Page<Track>> getAlbumTracks(long albumId) {
        return new GetByIdPagingDeezerRequest<>(albumId, albumService::getAlbumTracks);
    }
}
