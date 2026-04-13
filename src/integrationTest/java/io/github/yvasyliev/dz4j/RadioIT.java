package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.model.Genre;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Radio;
import io.github.yvasyliev.dz4j.model.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class RadioIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .build();
    }

    @Test
    void shouldReturnGenres() throws IOException {
        var body = read("/response/radio/get-genres.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Genre>>() {});

        stubFor(get(urlPathTemplate("/radio/genres")).willReturn(okJson(body)));

        assertEquals(expected, deezerClient.radio().getGenres());
    }

    @Test
    void shouldReturnLists() throws IOException {
        var body = read("/response/radio/get-lists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/radio/lists")).willReturn(okJson(body)));

        assertEquals(expected, deezerClient.radio().getLists());
    }

    @Test
    void shouldReturnListsWithPagination() throws IOException {
        var index = 5;
        var limit = 10;
        var body = read("/response/radio/get-lists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/radio/lists"))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.radio().getLists().index(index).limit(limit));
    }

    @Test
    void shouldReturnRadio() throws IOException {
        var radioId = 38305L;
        var body = read("/response/radio/get-radio.json");
        var expected = MAPPER.readValue(body, Radio.class);

        stubFor(get(urlPathTemplate("/radio/{radioId}"))
                .withPathParam("radioId", equalTo(radioId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.radio().getRadio(radioId));
    }

    @Test
    void shouldReturnRadios() throws IOException {
        var body = read("/response/radio/get-radios.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/radio")).willReturn(okJson(body)));

        assertEquals(expected, deezerClient.radio().getRadios());
    }

    @Test
    void shouldReturnTracks() throws IOException {
        var radioId = 38305L;
        var body = read("/response/radio/get-tracks.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/radio/{radioId}/tracks"))
                .withPathParam("radioId", equalTo(radioId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.radio().getTracks(radioId));
    }

    @Test
    void shouldReturnTracksWithPagination() throws IOException {
        var radioId = 38305L;
        var index = 5;
        var limit = 10;
        var body = read("/response/radio/get-tracks.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/radio/{radioId}/tracks"))
                .withPathParam("radioId", equalTo(radioId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.radio().getTracks(radioId).index(index).limit(limit));
    }
}
