package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.authorization.Authorization;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Episode;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Podcast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class PodcastIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .authorization(Authorization.of(new AccessToken(ACCESS_TOKEN)))
                .build();
    }

    @Test
    void shouldReturnEpisodes() throws IOException {
        var podcastId = 1447162L;
        var body = read("/response/podcast/get-episodes.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Episode>>() {});

        stubFor(get(urlPathTemplate("/podcast/{podcastId}/episodes"))
                .withPathParam("podcastId", equalTo(podcastId))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.podcast().getEpisodes(podcastId));
    }

    @Test
    void shouldReturnPodcast() throws IOException {
        var podcastId = 1447162L;
        var body = read("/response/podcast/get-podcast.json");
        var expected = MAPPER.readValue(body, Podcast.class);

        stubFor(get(urlPathTemplate("/podcast/{podcastId}"))
                .withPathParam("podcastId", equalTo(podcastId))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.podcast().getPodcast(podcastId));
    }
}
