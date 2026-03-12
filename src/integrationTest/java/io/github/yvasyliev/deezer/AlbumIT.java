package io.github.yvasyliev.deezer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class AlbumIT extends AbstractDeezerClientIT {
    private static final Supplier<Stream<Arguments>> testSuccessfulScenario = () -> {
        var albumId = 500138701L;
        var index = 5;
        var limit = 10;

        return Stream.of(
                arguments("album", new StubArguments<>(
                        "/album/{albumId}",
                        Map.of("albumId", albumId),
                        Map.of(),
                        "/response/album/get-album.json",
                        client -> client.album().getAlbum(albumId),
                        new TypeReference<>() {}
                )),
                arguments("fans", new StubArguments<>(
                        "/album/{albumId}/fans",
                        Map.of("albumId", albumId),
                        Map.of(),
                        "/response/album/get-fans.json",
                        client -> client.album().getFans(albumId),
                        new TypeReference<>() {}
                )),
                arguments("fans with pagination", new StubArguments<>(
                        "/album/{albumId}/fans",
                        Map.of("albumId", albumId),
                        Map.of(
                                "index", index,
                                "limit", limit
                        ),
                        "/response/album/get-fans.json",
                        client -> client.album().getFans(albumId).index(index).limit(limit),
                        new TypeReference<>() {}
                )),
                arguments("tracks", new StubArguments<>(
                        "/album/{albumId}/tracks",
                        Map.of("albumId", albumId),
                        Map.of(),
                        "/response/album/get-tracks.json",
                        client -> client.album().getTracks(albumId),
                        new TypeReference<>() {}
                )),
                arguments("tracks with pagination", new StubArguments<>(
                        "/album/{albumId}/tracks",
                        Map.of("albumId", albumId),
                        Map.of(
                                "index", index,
                                "limit", limit
                        ),
                        "/response/album/get-tracks.json",
                        client -> client.album().getTracks(albumId).index(index).limit(limit),
                        new TypeReference<>() {}
                ))
        );
    };

    @ParameterizedTest(name = "should return {0}", quoteTextArguments = false)
    @FieldSource
    void testSuccessfulScenario(String name, StubArguments<?> args) throws IOException {
        stubGet(args);
    }
}
