package io.github.yvasyliev.deezer;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
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

class ArtistIT extends AbstractDeezerClientIT {
    private static final Supplier<Stream<Arguments>> testSuccessfulScenario = () -> {
        var artistId = 5587890L;
        var index = 5;
        var limit = 10;

        return Stream.of(
                arguments("artist", new StubArguments<Artist>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}")))
                        .pathParam("artistId", artistId)
                        .file("/response/artist/get-artist.json")
                        .methodFactory(client -> client.artist().getArtist(artistId))
                        .type(new TypeReference<>() {})
                ),
                arguments("albums", new StubArguments<Page<Album>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/albums")))
                        .pathParam("artistId", artistId)
                        .file("/response/artist/get-albums.json")
                        .methodFactory(client -> client.artist().getAlbums(artistId))
                        .type(new TypeReference<>() {})
                ),
                arguments("albums with pagination", new StubArguments<Page<Album>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/albums")))
                        .pathParam("artistId", artistId)
                        .queryParam("index", index)
                        .queryParam("limit", limit)
                        .file("/response/artist/get-albums.json")
                        .methodFactory(client -> client.artist().getAlbums(artistId).index(index).limit(limit))
                        .type(new TypeReference<>() {})
                ),
                arguments("fans", new StubArguments<Page<User>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/fans")))
                        .pathParam("artistId", artistId)
                        .file("/response/artist/get-fans.json")
                        .methodFactory(client -> client.artist().getFans(artistId))
                        .type(new TypeReference<>() {})
                ),
                arguments("fans with pagination", new StubArguments<Page<User>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/fans")))
                        .pathParam("artistId", artistId)
                        .queryParam("index", index)
                        .queryParam("limit", limit)
                        .file("/response/artist/get-fans.json")
                        .methodFactory(client -> client.artist().getFans(artistId).index(index).limit(limit))
                        .type(new TypeReference<>() {})
                ),
                arguments("playlists", new StubArguments<Page<Playlist>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/playlists")))
                        .pathParam("artistId", artistId)
                        .file("/response/artist/get-playlists.json")
                        .methodFactory(client -> client.artist().getPlaylists(artistId))
                        .type(new TypeReference<>() {})
                ),
                arguments("playlists with pagination", new StubArguments<Page<Playlist>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/playlists")))
                        .pathParam("artistId", artistId)
                        .queryParam("index", index)
                        .queryParam("limit", limit)
                        .file("/response/artist/get-playlists.json")
                        .methodFactory(client -> client.artist().getPlaylists(artistId).index(index).limit(limit))
                        .type(new TypeReference<>() {})
                ),
                arguments("radio", new StubArguments<Page<Track>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/radio")))
                        .pathParam("artistId", artistId)
                        .file("/response/artist/get-radio.json")
                        .methodFactory(client -> client.artist().getRadio(artistId))
                        .type(new TypeReference<>() {})
                ),
                arguments("radio with pagination", new StubArguments<Page<Track>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/radio")))
                        .pathParam("artistId", artistId)
                        .queryParam("index", index)
                        .queryParam("limit", limit)
                        .file("/response/artist/get-radio.json")
                        .methodFactory(client -> client.artist().getRadio(artistId).index(index).limit(limit))
                        .type(new TypeReference<>() {})
                ),
                arguments("related", new StubArguments<Page<Artist>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/related")))
                        .pathParam("artistId", artistId)
                        .file("/response/artist/get-related.json")
                        .methodFactory(client -> client.artist().getRelated(artistId))
                        .type(new TypeReference<>() {})
                ),
                arguments("related with pagination", new StubArguments<Page<Artist>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/related")))
                        .pathParam("artistId", artistId)
                        .queryParam("index", index)
                        .queryParam("limit", limit)
                        .file("/response/artist/get-related.json")
                        .methodFactory(client -> client.artist().getRelated(artistId).index(index).limit(limit))
                        .type(new TypeReference<>() {})
                ),
                arguments("top", new StubArguments<Page<Track>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/top")))
                        .pathParam("artistId", artistId)
                        .file("/response/artist/get-top.json")
                        .methodFactory(client -> client.artist().getTop(artistId))
                        .type(new TypeReference<>() {})
                ),
                arguments("top with pagination", new StubArguments<Page<Track>>()
                        .mappingBuilder(get(urlPathTemplate("/artist/{artistId}/top")))
                        .pathParam("artistId", artistId)
                        .queryParam("index", index)
                        .queryParam("limit", limit)
                        .file("/response/artist/get-top.json")
                        .methodFactory(client -> client.artist().getTop(artistId).index(index).limit(limit))
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
