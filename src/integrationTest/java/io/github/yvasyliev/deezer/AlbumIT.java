package io.github.yvasyliev.deezer;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AlbumIT extends AbstractDeezerClientIT {
    private static final Supplier<Stream<Arguments>> testSuccessfulScenario = () -> {
        var albumId = 500138701L;
        var index = 5;
        var limit = 10;

        return Stream.of(
                arguments("album", new StubArguments<Album>()
                        .mappingBuilder(get(urlPathTemplate("/album/{albumId}")))
                        .pathParam("albumId", albumId)
                        .file("/response/album/get-album.json")
                        .methodFactory(client -> client.album().getAlbum(albumId))
                        .type(new TypeReference<>() {})
                ),
                arguments("fans", new StubArguments<Page<User>>()
                        .mappingBuilder(get(urlPathTemplate("/album/{albumId}/fans")))
                        .pathParam("albumId", albumId)
                        .file("/response/album/get-fans.json")
                        .methodFactory(client -> client.album().getFans(albumId))
                        .type(new TypeReference<>() {})
                ),
                arguments("fans with pagination", new StubArguments<Page<User>>()
                        .mappingBuilder(get(urlPathTemplate("/album/{albumId}/fans")))
                        .pathParam("albumId", albumId)
                        .queryParam("index", index)
                        .queryParam("limit", limit)
                        .file("/response/album/get-fans.json")
                        .methodFactory(client -> client.album().getFans(albumId).index(index).limit(limit))
                        .type(new TypeReference<>() {})
                ),
                arguments("tracks", new StubArguments<Page<Track>>()
                        .mappingBuilder(get(urlPathTemplate("/album/{albumId}/tracks")))
                        .pathParam("albumId", albumId)
                        .file("/response/album/get-tracks.json")
                        .methodFactory(client -> client.album().getTracks(albumId))
                        .type(new TypeReference<>() {})
                ),
                arguments("tracks with pagination", new StubArguments<Page<Track>>()
                        .mappingBuilder(get(urlPathTemplate("/album/{albumId}/tracks")))
                        .pathParam("albumId", albumId)
                        .queryParam("index", index)
                        .queryParam("limit", limit)
                        .file("/response/album/get-tracks.json")
                        .methodFactory(client -> client.album().getTracks(albumId).index(index).limit(limit))
                        .type(new TypeReference<>() {})
                )
        );
    };

    @ParameterizedTest(name = "should return {0}", quoteTextArguments = false)
    @FieldSource
    void testSuccessfulScenario(String name, StubArguments<?> args) throws IOException {
        stubRequest(args);
    }
}
