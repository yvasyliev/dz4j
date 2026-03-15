package io.github.yvasyliev.deezer;

import io.github.yvasyliev.deezer.model.BookmarkResponse;
import io.github.yvasyliev.deezer.model.Episode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EpisodeIT extends AbstractDeezerClientIT {
    private static final Supplier<Stream<Arguments>> testSuccessfulScenario = () -> {
        var episodeId = 605632582L;
        var offset = 42;

        return Stream.of(
                arguments("return episode", new StubArguments<Episode>()
                        .mappingBuilder(get(urlPathTemplate("/episode/{episodeId}")))
                        .pathParam("episodeId", episodeId)
                        .queryParam("access_token", ACCESS_TOKEN)
                        .file("/response/episode/get-episode.json")
                        .methodFactory(client -> client.episode().getEpisode(episodeId))
                        .type(new TypeReference<>() {})
                ),
                arguments("bookmark episode", new StubArguments<BookmarkResponse>()
                        .mappingBuilder(post(urlPathTemplate("/episode/{episodeId}/bookmark")))
                        .pathParam("episodeId", episodeId)
                        .formParam("access_token", ACCESS_TOKEN)
                        .formParam("offset", offset)
                        .file("/response/episode/bookmark-episode.json")
                        .methodFactory(client -> client.episode().bookmarkEpisode(episodeId, offset))
                        .type(new TypeReference<>() {})
                ),
                arguments("unbookmark episode", new StubArguments<BookmarkResponse>()
                        .mappingBuilder(delete(urlPathTemplate("/episode/{episodeId}/bookmark")))
                        .pathParam("episodeId", episodeId)
                        .queryParam("access_token", ACCESS_TOKEN)
                        .file("/response/episode/unbookmark-episode.json")
                        .methodFactory(client -> client.episode().unbookmarkEpisode(episodeId))
                        .type(new TypeReference<>() {})
                )
        );
    };

    @ParameterizedTest(name = "should {0}", quoteTextArguments = false)
    @FieldSource
    void testSuccessfulScenario(String name, StubArguments<?> args) throws IOException {
        stubRequest(args);
    }
}

