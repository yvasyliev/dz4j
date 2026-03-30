package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.authorization.Authorization;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Playlist;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class PlaylistIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .authorization(Authorization.of(new AccessToken(ACCESS_TOKEN)))
                .build();
    }

    @Test
    void shouldAddTracks() throws IOException {
        var playlistId = 10517702202L;
        var songs = "1807705517,1808237467";
        var body = read("/response/playlist/update-playlist.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/playlist/{playlistId}/tracks"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("songs", equalTo(songs))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().addTracks(playlistId, 1807705517L, 1808237467L));
    }

    @Test
    void shouldDeletePlaylist() throws IOException {
        var playlistId = 10517702202L;
        var body = read("/response/playlist/delete-playlist.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/playlist/{playlistId}"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().deletePlaylist(playlistId));
    }

    @Test
    void shouldDeleteTracks() throws IOException {
        var playlistId = 10517702202L;
        var songs = "1807705517,1808237467";
        var body = read("/response/playlist/delete-tracks.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/playlist/{playlistId}/tracks"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("songs", equalTo(songs))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().deleteTracks(playlistId, 1807705517L, 1808237467L));
    }

    @Test
    void shouldReturnFans() throws IOException {
        var playlistId = 10517702202L;
        var body = read("/response/playlist/get-fans.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/playlist/{playlistId}/fans"))
                .withPathParam("playlistId", equalTo(playlistId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().getFans(playlistId));
    }

    @Test
    void shouldReturnFansWithPagination() throws IOException {
        var playlistId = 10517702202L;
        var index = 5;
        var limit = 10;
        var body = read("/response/playlist/get-fans.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/playlist/{playlistId}/fans"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().getFans(playlistId).index(index).limit(limit));
    }

    @Test
    void shouldReturnPlaylist() throws IOException {
        var playlistId = 10517702202L;
        var body = read("/response/playlist/get-playlist.json");
        var expected = MAPPER.readValue(body, Playlist.class);

        stubFor(get(urlPathTemplate("/playlist/{playlistId}"))
                .withPathParam("playlistId", equalTo(playlistId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().getPlaylist(playlistId));
    }

    @Test
    void shouldReturnRadio() throws IOException {
        var playlistId = 10517702202L;
        var body = read("/response/playlist/get-radio.json");
        var expected = MAPPER.readValue(body, new TypeReference<Optional<Page<Track>>>() {});

        stubFor(get(urlPathTemplate("/playlist/{playlistId}/radio"))
                .withPathParam("playlistId", equalTo(playlistId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().getRadio(playlistId));
    }

    @Test
    void shouldReturnRadioWithPagination() throws IOException {
        var playlistId = 10517702202L;
        var index = 5;
        var limit = 10;
        var body = read("/response/playlist/get-radio.json");
        var expected = MAPPER.readValue(body, new TypeReference<Optional<Page<Track>>>() {});

        stubFor(get(urlPathTemplate("/playlist/{playlistId}/radio"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().getRadio(playlistId).index(index).limit(limit));
    }

    @Test
    void shouldReturnTracks() throws IOException {
        var playlistId = 10517702202L;
        var body = read("/response/playlist/get-tracks.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/playlist/{playlistId}/tracks"))
                .withPathParam("playlistId", equalTo(playlistId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().getTracks(playlistId));
    }

    @Test
    void shouldReturnTracksWithPagination() throws IOException {
        var playlistId = 10517702202L;
        var index = 5;
        var limit = 10;
        var body = read("/response/playlist/get-tracks.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/playlist/{playlistId}/tracks"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().getTracks(playlistId).index(index).limit(limit));
    }

    @Test
    void shouldMarkAsSeen() throws IOException {
        var playlistId = 10517702202L;
        var body = read("/response/playlist/mark-as-seen.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/playlist/{playlistId}/seen"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().markAsSeen(playlistId));
    }

    @Test
    void shouldOrderTracks() throws IOException {
        var playlistId = 10517702202L;
        var order = "1808237467,1807705517";
        var body = read("/response/playlist/order-tracks.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/playlist/{playlistId}/tracks"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("order", equalTo(order))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().orderTracks(playlistId, 1808237467L, 1807705517L));
    }

    @Test
    void shouldUpdatePlaylist() throws IOException {
        var playlistId = 10517702202L;
        var body = read("/response/playlist/update-playlist.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/playlist/{playlistId}"))
                .withPathParam("playlistId", equalTo(playlistId))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.playlist().updatePlaylist(playlistId));
    }
}
