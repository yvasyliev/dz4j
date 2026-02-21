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
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to charts.
 */
@RequiredArgsConstructor
public class ChartRequestFactory {
    private final ChartService chartService;

    /**
     * Returns a list of top albums in the specified genre.
     *
     * @param genreId genre ID
     * @return list of top albums in the specified genre
     */
    public PagingDeezerRequest<Page<Album>> getAlbumsChart(long genreId) {
        return new GetByIdPagingDeezerRequest<>(genreId, chartService::getAlbumsChart);
    }

    /**
     * Returns a list of top artists in the specified genre.
     *
     * @param genreId genre ID
     * @return list of top artists in the specified genre
     */
    public PagingDeezerRequest<Page<Artist>> getArtistsChart(long genreId) {
        return new GetByIdPagingDeezerRequest<>(genreId, chartService::getArtistsChart);
    }

    /**
     * Returns the overall chart for the specified genre.
     *
     * @param genreId genre ID
     * @return overall chart for the specified genre
     */
    public PagingDeezerRequest<Chart> getChart(long genreId) {
        return new GetByIdPagingDeezerRequest<>(genreId, chartService::getChart);
    }

    /**
     * Returns a list of top playlists in the specified genre.
     *
     * @param genreId genre ID
     * @return list of top playlists in the specified genre
     */
    public PagingDeezerRequest<Page<Playlist>> getPlaylistsChart(long genreId) {
        return new GetByIdPagingDeezerRequest<>(genreId, chartService::getPlaylistsChart);
    }

    /**
     * Returns a list of top podcasts in the specified genre.
     *
     * @param genreId genre ID
     * @return list of top podcasts in the specified genre
     */
    public PagingDeezerRequest<Page<Podcast>> getPodcastsChart(long genreId) {
        return new GetByIdPagingDeezerRequest<>(genreId, chartService::getPodcastsChart);
    }

    /**
     * Returns a list of top tracks in the specified genre.
     *
     * @param genreId genre ID
     * @return list of top tracks in the specified genre
     */
    public PagingDeezerRequest<Page<Track>> getTracksChart(long genreId) {
        return new GetByIdPagingDeezerRequest<>(genreId, chartService::getTracksChart);
    }
}
