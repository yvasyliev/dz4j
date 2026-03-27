package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Album;
import io.github.yvasyliev.dz4j.model.Artist;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Playlist;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.model.User;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.dz4j.request.IdDeezerRequest;
import io.github.yvasyliev.dz4j.request.PagingDeezerRequest;
import io.github.yvasyliev.dz4j.service.ArtistService;
import io.github.yvasyliev.dz4j.util.TriFunction;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * Factory for creating requests related to artists.
 */
@RequiredArgsConstructor
public class ArtistRequestFactory {
    private final ArtistService artistService;

    /**
     * Creates a request to get a list of artist's albums.
     *
     * @param artistId artist ID
     * @return request to get a list of artist's albums
     */
    public PagingDeezerRequest<Page<Album>> getAlbums(long artistId) {
        return createPagingDeezerRequest(artistId, artistService::getAlbums);
    }

    /**
     * Creates a request to get an artist.
     *
     * @param artistId artist ID
     * @return request to get an artist
     */
    public DeezerRequest<Artist> getArtist(long artistId) {
        return new IdDeezerRequest<>(artistId, artistService::getArtist);
    }

    /**
     * Creates a request to get a list of artist's fans.
     *
     * @param artistId artist ID
     * @return request to get a list of artist's fans
     */
    public PagingDeezerRequest<Page<User>> getFans(long artistId) {
        return createPagingDeezerRequest(artistId, artistService::getFans);
    }

    /**
     * Creates a request to get a list of artist's playlists.
     *
     * @param artistId artist ID
     * @return request to get a list of artist's playlists
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylists(long artistId) {
        return createPagingDeezerRequest(artistId, artistService::getPlaylists);
    }

    /**
     * Creates a request to get a list of tracks.
     *
     * @param artistId artist ID
     * @return request to get a list of tracks
     */
    public PagingDeezerRequest<Page<Track>> getRadio(long artistId) {
        return createPagingDeezerRequest(artistId, artistService::getRadio);
    }

    /**
     * Creates a request to get a list of related artists.
     *
     * @param artistId artist ID
     * @return request to get a list of related artists
     */
    public PagingDeezerRequest<Page<Artist>> getRelated(long artistId) {
        return createPagingDeezerRequest(artistId, artistService::getRelated);
    }

    /**
     * Creates a request to get a list of artist's top tracks.
     *
     * @param artistId artist ID
     * @return request to get a list of artist's top tracks
     */
    public PagingDeezerRequest<Page<Track>> getTop(long artistId) {
        return createPagingDeezerRequest(artistId, artistService::getTop);
    }

    private <T> PagingDeezerRequest<Page<T>> createPagingDeezerRequest(
            long artistId,
            TriFunction<Long, Integer, Integer, CompletableFuture<Page<T>>> asyncMethod
    ) {
        return new GetByIdPagingDeezerRequest<>(artistId, asyncMethod);
    }
}
