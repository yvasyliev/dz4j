package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Chart;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Podcast;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.deezer.request.PagingDeezerRequest;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.util.TriFunction;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * Factory for creating requests related to charts.
 */
@RequiredArgsConstructor
public class ChartRequestFactory {
    private final ChartService chartService;

    /**
     * Creates a request to get a list of top albums in the specified genre.
     *
     * @param genreId genre ID
     * @return request to get a list of top albums in the specified genre
     */
    public PagingDeezerRequest<Page<Album>> getAlbumsChart(long genreId) {
        return createPagingDeezerRequest(genreId, chartService::getAlbumsChart);
    }

    /**
     * Creates a request to get a list of top artists in the specified genre.
     *
     * @param genreId genre ID
     * @return request to get a list of top artists in the specified genre
     */
    public PagingDeezerRequest<Page<Artist>> getArtistsChart(long genreId) {
        return createPagingDeezerRequest(genreId, chartService::getArtistsChart);
    }

    /**
     * Creates a request to get the overall chart for the specified genre.
     *
     * @param genreId genre ID
     * @return request to get the overall chart for the specified genre
     */
    public PagingDeezerRequest<Chart> getChart(long genreId) {
        return createPagingDeezerRequest(genreId, chartService::getChart);
    }

    /**
     * Creates a request to get a list of top playlists in the specified genre.
     *
     * @param genreId genre ID
     * @return request to get a list of top playlists in the specified genre
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylistsChart(long genreId) {
        return createPagingDeezerRequest(genreId, chartService::getPlaylistsChart);
    }

    /**
     * Creates a request to get a list of top podcasts in the specified genre.
     *
     * @param genreId genre ID
     * @return request to get a list of top podcasts in the specified genre
     */
    public PagingDeezerRequest<Page<Podcast>> getPodcastsChart(long genreId) {
        return createPagingDeezerRequest(genreId, chartService::getPodcastsChart);
    }

    /**
     * Creates a request to get a list of top tracks in the specified genre.
     *
     * @param genreId genre ID
     * @return request to get a list of top tracks in the specified genre
     */
    public PagingDeezerRequest<Page<Track>> getTracksChart(long genreId) {
        return createPagingDeezerRequest(genreId, chartService::getTracksChart);
    }

    private <T> PagingDeezerRequest<T> createPagingDeezerRequest(
            long artistId,
            TriFunction<Long, Integer, Integer, CompletableFuture<T>> asyncMethod
    ) {
        return new GetByIdPagingDeezerRequest<>(artistId, asyncMethod);
    }
}
