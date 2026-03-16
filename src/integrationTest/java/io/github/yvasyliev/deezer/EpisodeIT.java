package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.yvasyliev.deezer.model.BookmarkResponse;
import io.github.yvasyliev.deezer.model.Episode;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.util.DeezerDefaults;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

@WireMockTest
class EpisodeIT {
    private static final String ACCESS_TOKEN = "test_access_token";
    private static final JsonMapper MAPPER = DeezerDefaults.jsonMapper();
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .authorization(ACCESS_TOKEN)
                .build();
    }

    @Test
    void shouldReturnEpisode() throws IOException {
        var episodeId = 605632582L;
        var body = read("/response/episode/get-episode.json");
        var expected = MAPPER.readValue(body, Episode.class);

        stubFor(get(urlPathTemplate("/episode/{episodeId}"))
                .withPathParam("episodeId", equalTo(String.valueOf(episodeId)))
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
                .withPathParam("episodeId", equalTo(String.valueOf(episodeId)))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("offset", equalTo(String.valueOf(offset)))
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
                .withPathParam("episodeId", equalTo(String.valueOf(episodeId)))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.episode().unbookmarkEpisode(episodeId));
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

