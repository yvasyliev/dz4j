package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.model.Track;
import org.junit.jupiter.api.BeforeEach;
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
                .authorization(ACCESS_TOKEN)
                .build();
    }

    @Test
    void shouldReturnTrack() throws IOException {
        var trackId = 541999L;
        var body = read("/response/track/get-track.json");
        var expected = MAPPER.readValue(body, Track.class);

        stubFor(get(urlPathTemplate("/track/{trackId}"))
                .withPathParam("trackId", equalTo(trackId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.track().getTrack(trackId));
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
}
