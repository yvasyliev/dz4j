package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Chart;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Podcast;
import io.github.yvasyliev.deezer.model.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class ChartIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .build();
    }

    @Test
    void shouldReturnAlbumsChart() throws IOException {
        var genreId = 1L;
        var body = read("/response/chart/get-albums-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/albums"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getAlbumsChart(genreId));
    }

    @Test
    void shouldReturnAlbumsChartWithPagination() throws IOException {
        var genreId = 1L;
        var index = 5;
        var limit = 10;
        var body = read("/response/chart/get-albums-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/albums"))
                .withPathParam("genreId", equalTo(genreId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getAlbumsChart(genreId).index(index).limit(limit));
    }

    @Test
    void shouldReturnArtistsChart() throws IOException {
        var genreId = 1L;
        var body = read("/response/chart/get-artists-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/artists"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getArtistsChart(genreId));
    }

    @Test
    void shouldReturnArtistsChartWithPagination() throws IOException {
        var genreId = 1L;
        var index = 5;
        var limit = 10;
        var body = read("/response/chart/get-artists-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/artists"))
                .withPathParam("genreId", equalTo(genreId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getArtistsChart(genreId).index(index).limit(limit));
    }

    @Test
    void shouldReturnChart() throws IOException {
        var genreId = 1L;
        var body = read("/response/chart/get-chart.json");
        var expected = MAPPER.readValue(body, Chart.class);

        stubFor(get(urlPathTemplate("/chart/{genreId}"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getChart(genreId));
    }

    @Test
    void shouldReturnChartWithPagination() throws IOException {
        var genreId = 1L;
        var index = 5;
        var limit = 10;
        var body = read("/response/chart/get-chart.json");
        var expected = MAPPER.readValue(body, Chart.class);

        stubFor(get(urlPathTemplate("/chart/{genreId}"))
                .withPathParam("genreId", equalTo(genreId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getChart(genreId).index(index).limit(limit));
    }

    @Test
    void shouldReturnPlaylistsChart() throws IOException {
        var genreId = 1L;
        var body = read("/response/chart/get-playlists-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/playlists"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getPlaylistsChart(genreId));
    }

    @Test
    void shouldReturnPlaylistsChartWithPagination() throws IOException {
        var genreId = 1L;
        var index = 5;
        var limit = 10;
        var body = read("/response/chart/get-playlists-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/playlists"))
                .withPathParam("genreId", equalTo(genreId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getPlaylistsChart(genreId).index(index).limit(limit));
    }

    @Test
    void shouldReturnPodcastsChart() throws IOException {
        var genreId = 1L;
        var body = read("/response/chart/get-podcasts-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Podcast>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/podcasts"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getPodcastsChart(genreId));
    }

    @Test
    void shouldReturnPodcastsChartWithPagination() throws IOException {
        var genreId = 1L;
        var index = 5;
        var limit = 10;
        var body = read("/response/chart/get-podcasts-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Podcast>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/podcasts"))
                .withPathParam("genreId", equalTo(genreId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getPodcastsChart(genreId).index(index).limit(limit));
    }

    @Test
    void shouldReturnTracksChart() throws IOException {
        var genreId = 1L;
        var body = read("/response/chart/get-tracks-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/tracks"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getTracksChart(genreId));
    }

    @Test
    void shouldReturnTracksChartWithPagination() throws IOException {
        var genreId = 1L;
        var index = 5;
        var limit = 10;
        var body = read("/response/chart/get-tracks-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/chart/{genreId}/tracks"))
                .withPathParam("genreId", equalTo(genreId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.chart().getTracksChart(genreId).index(index).limit(limit));
    }
}
