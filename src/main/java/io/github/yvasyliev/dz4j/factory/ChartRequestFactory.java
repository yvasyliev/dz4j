package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Album;
import io.github.yvasyliev.dz4j.model.Artist;
import io.github.yvasyliev.dz4j.model.Chart;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Playlist;
import io.github.yvasyliev.dz4j.model.Podcast;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.dz4j.request.PagingDeezerRequest;
import io.github.yvasyliev.dz4j.service.ChartService;
import io.github.yvasyliev.dz4j.util.TriFunction;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to charts.
 */
@RequiredArgsConstructor
public class ChartRequestFactory {
    private final ChartService chartService;

    private static <T> PagingDeezerRequest<T> createPagingDeezerRequest(
            long artistId, TriFunction<Long, Integer, Integer, CompletableFuture<T>> asyncMethod) {
        return new GetByIdPagingDeezerRequest<>(artistId, asyncMethod);
    }

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
}
