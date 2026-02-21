package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdDeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.deezer.request.PagingDeezerRequest;
import io.github.yvasyliev.deezer.service.ArtistService;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to artists.
 */
@RequiredArgsConstructor
public class ArtistRequestFactory {
    private final ArtistService artistService;

    /**
     * Returns a list of artist's albums.
     *
     * @param artistId artist ID
     * @return list of artist's albums
     */
    public PagingDeezerRequest<Album> getAlbums(long artistId) {
        return new GetByIdPagingDeezerRequest<>(artistId, artistService::getAlbums);
    }

    /**
     * Returns an artist.
     *
     * @param artistId artist ID
     * @return artist
     */
    public DeezerRequest<Artist> getArtist(long artistId) {
        return new GetByIdDeezerRequest<>(artistId, artistService::getArtist);
    }

    /**
     * Returns a list of artist's fans.
     *
     * @param artistId artist ID
     * @return list of artist's fans
     */
    public PagingDeezerRequest<User> getFans(long artistId) {
        return new GetByIdPagingDeezerRequest<>(artistId, artistService::getFans);
    }

    /**
     * Returns a list of artist's playlists.
     *
     * @param artistId artist ID
     * @return list of artist's playlists
     */
    public PagingDeezerRequest<Playlist> getPlaylists(long artistId) {
        return new GetByIdPagingDeezerRequest<>(artistId, artistService::getPlaylists);
    }

    /**
     * Returns a list of tracks.
     *
     * @param artistId artist ID
     * @return list of tracks
     */
    public PagingDeezerRequest<Track> getRadio(long artistId) {
        return new GetByIdPagingDeezerRequest<>(artistId, artistService::getRadio);
    }

    /**
     * Returns a list of related artists.
     *
     * @param artistId artist ID
     * @return list of related artists
     */
    public PagingDeezerRequest<Artist> getRelated(long artistId) {
        return new GetByIdPagingDeezerRequest<>(artistId, artistService::getRelated);
    }

    /**
     * Returns a list of artist's top tracks.
     *
     * @param artistId artist ID
     * @return list of artist's top tracks
     */
    public PagingDeezerRequest<Track> getTop(long artistId) {
        return new GetByIdPagingDeezerRequest<>(artistId, artistService::getTop);
    }
}
