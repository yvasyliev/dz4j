package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.deezer.model.BookmarkResponse;
import io.github.yvasyliev.deezer.model.Episode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class EpisodeIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .authorization(ACCESS_TOKEN)
                .build();
    }

    @Test
    void shouldReturnEpisode() throws IOException {
        var episodeId = 605632582L;
        var body = read("/response/episode/get-episode.json");
        var expected = MAPPER.readValue(body, Episode.class);

        stubFor(get(urlPathTemplate("/episode/{episodeId}"))
                .withPathParam("episodeId", equalTo(episodeId))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.episode().getEpisode(episodeId));
    }

    @Test
    void shouldBookmarkEpisode() throws IOException {
        var episodeId = 605632582L;
        var offset = 42;
        var body = read("/response/episode/bookmark-episode.json");
        var expected = MAPPER.readValue(body, BookmarkResponse.class);

        stubFor(post(urlPathTemplate("/episode/{episodeId}/bookmark"))
                .withPathParam("episodeId", equalTo(episodeId))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("offset", equalTo(offset))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.episode().bookmarkEpisode(episodeId, offset));
    }

    @Test
    void shouldUnbookmarkEpisode() throws IOException {
        var episodeId = 605632582L;
        var body = read("/response/episode/unbookmark-episode.json");
        var expected = MAPPER.readValue(body, BookmarkResponse.class);

        stubFor(delete(urlPathTemplate("/episode/{episodeId}/bookmark"))
                .withPathParam("episodeId", equalTo(episodeId))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.episode().unbookmarkEpisode(episodeId));
    }
}

