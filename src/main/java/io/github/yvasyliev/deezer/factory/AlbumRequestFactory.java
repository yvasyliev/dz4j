package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdDeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.deezer.service.AlbumService;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to albums.
 */
@RequiredArgsConstructor
public class AlbumRequestFactory {
    private final AlbumService albumService;

    /**
     * Returns an album.
     *
     * @param albumId album ID
     * @return album
     */
    public DeezerRequest<Album> getAlbum(long albumId) {
        return new GetByIdDeezerRequest<>(albumId, albumService::getAlbum);
    }

    /**
     * Returns a list of album's fans.
     *
     * @param albumId album ID
     * @return list of album's fans
     */
    public GetByIdPagingDeezerRequest<User> getAlbumFans(long albumId) {
        return new GetByIdPagingDeezerRequest<>(albumId, albumService::getAlbumFans);
    }

    /**
     * Returns a list of album's tracks.
     *
     * @param albumId album ID
     * @return list of album's tracks
     */
    public GetByIdPagingDeezerRequest<Track> getAlbumTracks(long albumId) {
        return new GetByIdPagingDeezerRequest<>(albumId, albumService::getAlbumTracks);
    }
}
