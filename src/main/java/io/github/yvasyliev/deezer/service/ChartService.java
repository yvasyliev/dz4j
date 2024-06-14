package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Podcast;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ChartService extends DeezerService {
    String CHART = "/chart";
    String CHART_ID = "/chart/{chartId}";
    String CHART_ALBUMS = "/chart/{chartId}/albums";
    String CHART_ARTISTS = "/chart/{chartId}/artists";
    String CHART_PLAYLISTS = "/chart/{chartId}/playlists";
    String CHART_PODCASTS = "/chart/{chartId}/podcasts";
    String CHART_TRACKS = "/chart/{chartId}/tracks";

    @RequestLine(GET + CHART)
    Chart getChart();

    @RequestLine(GET + CHART)
    CompletableFuture<Chart> getChartAsync();

    @RequestLine(GET + CHART_ID)
    Chart getChart(@Param("chartId") long chartId);

    @RequestLine(GET + CHART_ID)
    CompletableFuture<Chart> getChartAsync(@Param("chartId") long chartId);

    @RequestLine(GET + CHART_ALBUMS)
    Page<Album, PagingMethod<Album>> getChartAlbums(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_ALBUMS)
    CompletableFuture<Page<Album, PagingMethod<Album>>> getChartAlbumsAsync(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_ARTISTS)
    Page<Artist, PagingMethod<Artist>> getChartArtists(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_ARTISTS)
    CompletableFuture<Page<Artist, PagingMethod<Artist>>> getChartArtistsAsync(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_PLAYLISTS)
    Page<Playlist, PagingMethod<Playlist>> getChartPlaylists(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_PLAYLISTS)
    CompletableFuture<Page<Playlist, PagingMethod<Playlist>>> getChartPlaylistsAsync(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_PODCASTS)
    Page<Podcast, PagingMethod<Podcast>> getChartPodcasts(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_PODCASTS)
    CompletableFuture<Page<Podcast, PagingMethod<Podcast>>> getChartPodcastsAsync(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_TRACKS)
    Page<Track, PagingMethod<Track>> getChartTracks(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + CHART_TRACKS)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getChartTracksAsync(@Param("chartId") long chartId, @QueryMap Map<String, Object> queryParams);
}
