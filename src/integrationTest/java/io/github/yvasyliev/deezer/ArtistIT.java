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

class ArtistIT extends AbstractDeezerClientIT {
    private static final Supplier<Stream<Arguments>> testSuccessfulScenario = () -> {
        var artistId = 5587890L;
        var index = 5;
        var limit = 10;

        return Stream.of(
                arguments("artist", new StubArguments<>(
                        "/artist/{artistId}",
                        Map.of("artistId", artistId),
                        Map.of(),
                        "/response/artist/get-artist.json",
                        client -> client.artist().getArtist(artistId),
                        new TypeReference<>() {}
                )),
                arguments("albums", new StubArguments<>(
                        "/artist/{artistId}/albums",
                        Map.of("artistId", artistId),
                        Map.of(),
                        "/response/artist/get-albums.json",
                        client -> client.artist().getAlbums(artistId),
                        new TypeReference<>() {}
                )),
                arguments("albums with pagination", new StubArguments<>(
                        "/artist/{artistId}/albums",
                        Map.of("artistId", artistId),
                        Map.of(
                                "index", index,
                                "limit", limit
                        ),
                        "/response/artist/get-albums.json",
                        client -> client.artist().getAlbums(artistId).index(index).limit(limit),
                        new TypeReference<>() {}
                )),
                arguments("fans", new StubArguments<>(
                        "/artist/{artistId}/fans",
                        Map.of("artistId", artistId),
                        Map.of(),
                        "/response/artist/get-fans.json",
                        client -> client.artist().getFans(artistId),
                        new TypeReference<>() {}
                )),
                arguments("fans with pagination", new StubArguments<>(
                        "/artist/{artistId}/fans",
                        Map.of("artistId", artistId),
                        Map.of(
                                "index", index,
                                "limit", limit
                        ),
                        "/response/artist/get-fans.json",
                        client -> client.artist().getFans(artistId).index(index).limit(limit),
                        new TypeReference<>() {}
                )),
                arguments("playlists", new StubArguments<>(
                        "/artist/{artistId}/playlists",
                        Map.of("artistId", artistId),
                        Map.of(),
                        "/response/artist/get-playlists.json",
                        client -> client.artist().getPlaylists(artistId),
                        new TypeReference<>() {}
                )),
                arguments("playlists with pagination", new StubArguments<>(
                        "/artist/{artistId}/playlists",
                        Map.of("artistId", artistId),
                        Map.of(
                                "index", index,
                                "limit", limit
                        ),
                        "/response/artist/get-playlists.json",
                        client -> client.artist().getPlaylists(artistId).index(index).limit(limit),
                        new TypeReference<>() {}
                )),
                arguments("radio", new StubArguments<>(
                        "/artist/{artistId}/radio",
                        Map.of("artistId", artistId),
                        Map.of(),
                        "/response/artist/get-radio.json",
                        client -> client.artist().getRadio(artistId),
                        new TypeReference<>() {}
                )),
                arguments("radio with pagination", new StubArguments<>(
                        "/artist/{artistId}/radio",
                        Map.of("artistId", artistId),
                        Map.of(
                                "index", index,
                                "limit", limit
                        ),
                        "/response/artist/get-radio.json",
                        client -> client.artist().getRadio(artistId).index(index).limit(limit),
                        new TypeReference<>() {}
                )),
                arguments("related", new StubArguments<>(
                        "/artist/{artistId}/related",
                        Map.of("artistId", artistId),
                        Map.of(),
                        "/response/artist/get-related.json",
                        client -> client.artist().getRelated(artistId),
                        new TypeReference<>() {}
                )),
                arguments("related with pagination", new StubArguments<>(
                        "/artist/{artistId}/related",
                        Map.of("artistId", artistId),
                        Map.of(
                                "index", index,
                                "limit", limit
                        ),
                        "/response/artist/get-related.json",
                        client -> client.artist().getRelated(artistId).index(index).limit(limit),
                        new TypeReference<>() {}
                )),
                arguments("top", new StubArguments<>(
                        "/artist/{artistId}/top",
                        Map.of("artistId", artistId),
                        Map.of(),
                        "/response/artist/get-top.json",
                        client -> client.artist().getTop(artistId),
                        new TypeReference<>() {}
                )),
                arguments("top with pagination", new StubArguments<>(
                        "/artist/{artistId}/top",
                        Map.of("artistId", artistId),
                        Map.of(
                                "index", index,
                                "limit", limit
                        ),
                        "/response/artist/get-top.json",
                        client -> client.artist().getTop(artistId).index(index).limit(limit),
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
