package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.authorization.Authorization;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class TrackIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .authorization(Authorization.of(new AccessToken(ACCESS_TOKEN)))
                .build();
    }

    @Test
    void shouldReturnTrack() throws IOException {
        shouldReturnTrack(541999L, "/response/track/get-track.json");
    }

    @Test
    @DisplayName("GH-204: should return track for id=3135556")
    void shouldReturn3135556Track() throws IOException {
        shouldReturnTrack(3135556L, "/response/track/get-3135556-track.json");
    }

    @Test
    void shouldUpdateTrack() throws IOException {
        var trackId = 541999L;
        var title = "My Track";
        var artist = "My Artist";
        var album = "My Album";
        var body = read("/response/track/update-track.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/track/{trackId}"))
                .withPathParam("trackId", equalTo(trackId))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("title", equalTo(title))
                .withFormParam("artist", equalTo(artist))
                .withFormParam("album", equalTo(album))
                .willReturn(okJson(body))
        );

        assertEquals(
                expected,
                deezerClient.track().updateTrack(trackId).title(title).artist(artist).album(album)
        );
    }

    @SuppressWarnings("checkstyle:OverloadMethodsDeclarationOrder")
    private void shouldReturnTrack(long trackId, String file) throws IOException {
        var body = read(file);
        var expected = MAPPER.readValue(body, Track.class);

        stubFor(get(urlPathTemplate("/track/{trackId}"))
                .withPathParam("trackId", equalTo(trackId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.track().getTrack(trackId));
    }
}
