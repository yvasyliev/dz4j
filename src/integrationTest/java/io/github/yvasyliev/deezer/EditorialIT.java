package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Chart;
import io.github.yvasyliev.deezer.model.Editorial;
import io.github.yvasyliev.deezer.model.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class EditorialIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .build();
    }

    @Test
    void shouldReturnEditorials() throws IOException {
        var body = read("/response/editorial/get-editorials.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Editorial>>() {});

        stubFor(get(urlEqualTo("/editorial")).willReturn(okJson(body)));

        assertEquals(expected, deezerClient.editorial().getEditorials());
    }

    @Test
    void shouldReturnEditorialsWithPagination() throws IOException {
        var index = 5;
        var limit = 10;
        var body = read("/response/editorial/get-editorials.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Editorial>>() {});

        stubFor(get(urlPathTemplate("/editorial"))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.editorial().getEditorials().index(index).limit(limit));
    }

    @Test
    void shouldReturnEditorial() throws IOException {
        var genreId = 152L;
        var body = read("/response/editorial/get-editorial.json");
        var expected = MAPPER.readValue(body, Editorial.class);

        stubFor(get(urlPathTemplate("/editorial/{genreId}"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.editorial().getEditorial(genreId));
    }

    @Test
    void shouldReturnEditorialSelection() throws IOException {
        var genreId = 152L;
        var body = read("/response/editorial/get-editorial-selection.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/editorial/{genreId}/selection"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.editorial().getEditorialSelection(genreId));
    }

    @Test
    void shouldReturnEditorialReleases() throws IOException {
        var genreId = 152L;
        var body = read("/response/editorial/get-editorial-releases.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/editorial/{genreId}/releases"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.editorial().getEditorialReleases(genreId));
    }

    @Test
    void shouldReturnEditorialReleasesWithPagination() throws IOException {
        var genreId = 152L;
        var index = 5;
        var limit = 10;
        var body = read("/response/editorial/get-editorial-releases.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/editorial/{genreId}/releases"))
                .withPathParam("genreId", equalTo(genreId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.editorial().getEditorialReleases(genreId).index(index).limit(limit));
    }

    @Test
    void shouldReturnEditorialCharts() throws IOException {
        var genreId = 152L;
        var body = read("/response/editorial/get-editorial-charts.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Chart>>() {});

        stubFor(get(urlPathTemplate("/editorial/{genreId}/charts"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.editorial().getEditorialCharts(genreId));
    }

    @Test
    void shouldReturnEditorialChartsWithPagination() throws IOException {
        var genreId = 152L;
        var index = 5;
        var limit = 10;
        var body = read("/response/editorial/get-editorial-charts.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Chart>>() {});

        stubFor(get(urlPathTemplate("/editorial/{genreId}/charts"))
                .withPathParam("genreId", equalTo(genreId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.editorial().getEditorialCharts(genreId).index(index).limit(limit));
    }
}
