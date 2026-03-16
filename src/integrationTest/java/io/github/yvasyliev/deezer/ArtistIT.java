package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.util.DeezerDefaults;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

@WireMockTest
class ArtistIT {
    private static final JsonMapper MAPPER = DeezerDefaults.jsonMapper();
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .build();
    }

    @Test
    void shouldReturnArtist() throws IOException {
        var artistId = 5587890L;
        var body = read("/response/artist/get-artist.json");
        var expected = MAPPER.readValue(body, Artist.class);

        stubFor(get(urlPathTemplate("/artist/{artistId}"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getArtist(artistId));
    }

    @Test
    void shouldReturnAlbums() throws IOException {
        var artistId = 5587890L;
        var body = read("/response/artist/get-albums.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/albums"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getAlbums(artistId));
    }

    @Test
    void shouldReturnAlbumsWithPagination() throws IOException {
        var artistId = 5587890L;
        var index = 5;
        var limit = 10;
        var body = read("/response/artist/get-albums.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/albums"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .withQueryParam("index", equalTo(String.valueOf(index)))
                .withQueryParam("limit", equalTo(String.valueOf(limit)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getAlbums(artistId).index(index).limit(limit));
    }

    @Test
    void shouldReturnFans() throws IOException {
        var artistId = 5587890L;
        var body = read("/response/artist/get-fans.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/fans"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getFans(artistId));
    }

    @Test
    void shouldReturnFansWithPagination() throws IOException {
        var artistId = 5587890L;
        var index = 5;
        var limit = 10;
        var body = read("/response/artist/get-fans.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/fans"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .withQueryParam("index", equalTo(String.valueOf(index)))
                .withQueryParam("limit", equalTo(String.valueOf(limit)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getFans(artistId).index(index).limit(limit));
    }

    @Test
    void shouldReturnPlaylists() throws IOException {
        var artistId = 5587890L;
        var body = read("/response/artist/get-playlists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/playlists"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getPlaylists(artistId));
    }

    @Test
    void shouldReturnPlaylistsWithPagination() throws IOException {
        var artistId = 5587890L;
        var index = 5;
        var limit = 10;
        var body = read("/response/artist/get-playlists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/playlists"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .withQueryParam("index", equalTo(String.valueOf(index)))
                .withQueryParam("limit", equalTo(String.valueOf(limit)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getPlaylists(artistId).index(index).limit(limit));
    }

    @Test
    void shouldReturnRadio() throws IOException {
        var artistId = 5587890L;
        var body = read("/response/artist/get-radio.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/radio"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getRadio(artistId));
    }

    @Test
    void shouldReturnRadioWithPagination() throws IOException {
        var artistId = 5587890L;
        var index = 5;
        var limit = 10;
        var body = read("/response/artist/get-radio.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/radio"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .withQueryParam("index", equalTo(String.valueOf(index)))
                .withQueryParam("limit", equalTo(String.valueOf(limit)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getRadio(artistId).index(index).limit(limit));
    }

    @Test
    void shouldReturnRelated() throws IOException {
        var artistId = 5587890L;
        var body = read("/response/artist/get-related.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/related"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getRelated(artistId));
    }

    @Test
    void shouldReturnRelatedWithPagination() throws IOException {
        var artistId = 5587890L;
        var index = 5;
        var limit = 10;
        var body = read("/response/artist/get-related.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/related"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .withQueryParam("index", equalTo(String.valueOf(index)))
                .withQueryParam("limit", equalTo(String.valueOf(limit)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getRelated(artistId).index(index).limit(limit));
    }

    @Test
    void shouldReturnTop() throws IOException {
        var artistId = 5587890L;
        var body = read("/response/artist/get-top.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/top"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getTop(artistId));
    }

    @Test
    void shouldReturnTopWithPagination() throws IOException {
        var artistId = 5587890L;
        var index = 5;
        var limit = 10;
        var body = read("/response/artist/get-top.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/artist/{artistId}/top"))
                .withPathParam("artistId", equalTo(String.valueOf(artistId)))
                .withQueryParam("index", equalTo(String.valueOf(index)))
                .withQueryParam("limit", equalTo(String.valueOf(limit)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.artist().getTop(artistId).index(index).limit(limit));
    }

    private <T> void assertEquals(T expected, DeezerRequest<T> request) {
        Assertions.assertEquals(expected, request.execute());
        Assertions.assertEquals(expected, request.executeAsync().join());
    }

    private String read(String file) throws IOException {
        @Cleanup var inputStream = Objects.requireNonNull(this.getClass().getResourceAsStream(file));

        return new String(inputStream.readAllBytes());
    }
}
