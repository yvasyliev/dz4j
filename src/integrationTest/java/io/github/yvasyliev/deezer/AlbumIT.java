package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class AlbumIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .build();
    }

    @Test
    void shouldReturnAlbum() throws IOException {
        var albumId = 500138701L;
        var body = read("/response/album/get-album.json");
        var expected = MAPPER.readValue(body, Album.class);

        stubFor(get(urlPathTemplate("/album/{albumId}"))
                .withPathParam("albumId", equalTo(String.valueOf(albumId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.album().getAlbum(albumId));
    }

    @Test
    void shouldReturnFans() throws IOException {
        var albumId = 500138701L;
        var body = read("/response/album/get-fans.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/album/{albumId}/fans"))
                .withPathParam("albumId", equalTo(String.valueOf(albumId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.album().getFans(albumId));
    }

    @Test
    void shouldReturnFansWithPagination() throws IOException {
        var albumId = 500138701L;
        var index = 5;
        var limit = 10;
        var body = read("/response/album/get-fans.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/album/{albumId}/fans"))
                .withPathParam("albumId", equalTo(String.valueOf(albumId)))
                .withQueryParam("index", equalTo(String.valueOf(index)))
                .withQueryParam("limit", equalTo(String.valueOf(limit)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.album().getFans(albumId).index(index).limit(limit));
    }

    @Test
    void shouldReturnTracks() throws IOException {
        var albumId = 500138701L;
        var body = read("/response/album/get-tracks.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/album/{albumId}/tracks"))
                .withPathParam("albumId", equalTo(String.valueOf(albumId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.album().getTracks(albumId));
    }

    @Test
    void shouldReturnTracksWithPagination() throws IOException {
        var albumId = 500138701L;
        var index = 5;
        var limit = 10;
        var body = read("/response/album/get-tracks.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/album/{albumId}/tracks"))
                .withPathParam("albumId", equalTo(String.valueOf(albumId)))
                .withQueryParam("index", equalTo(String.valueOf(index)))
                .withQueryParam("limit", equalTo(String.valueOf(limit)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.album().getTracks(albumId).index(index).limit(limit));
    }
}
