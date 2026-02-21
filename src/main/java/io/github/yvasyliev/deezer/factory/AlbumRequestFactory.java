package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.request.DeezerPagingRequest;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
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
    public DeezerRequest<Album> getAlbum(int albumId) {
        return new SimpleDeezerRequest<>(() -> albumService.getAlbum(albumId));
    }

    /**
     * Returns a list of album's fans.
     *
     * @param albumId album ID
     * @return list of album's fans
     */
    public DeezerPagingRequest<User> getAlbumFans(int albumId) {
        return new DeezerPagingRequest<>((index, limit) -> albumService.getAlbumFans(albumId, index, limit));
    }

    /**
     * Returns a list of album's tracks.
     *
     * @param albumId album ID
     * @return list of album's tracks
     */
    public DeezerPagingRequest<Track> getAlbumTracks(int albumId) {
        return new DeezerPagingRequest<>((index, limit) -> albumService.getAlbumTracks(albumId, index, limit));
    }
}
