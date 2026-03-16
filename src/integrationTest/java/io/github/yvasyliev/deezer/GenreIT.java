package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Genre;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Radio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class GenreIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .build();
    }

    @Test
    void shouldReturnGenres() throws IOException {
        var body = read("/response/genre/get-genres.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Genre>>() {});

        stubFor(get(urlEqualTo("/genre")).willReturn(okJson(body)));

        assertEquals(expected, deezerClient.genre().getGenres());
    }

    @Test
    void shouldReturnGenre() throws IOException {
        var genreId = 152L;
        var body = read("/response/genre/get-genre.json");
        var expected = MAPPER.readValue(body, Genre.class);

        stubFor(get(urlPathTemplate("/genre/{genreId}"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.genre().getGenre(genreId));
    }

    @Test
    void shouldReturnArtists() throws IOException {
        var genreId = 152L;
        var body = read("/response/genre/get-artists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/genre/{genreId}/artists"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.genre().getArtists(genreId));
    }

    @Test
    void shouldReturnRadios() throws IOException {
        var genreId = 152L;
        var body = read("/response/genre/get-radios.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/genre/{genreId}/radios"))
                .withPathParam("genreId", equalTo(genreId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.genre().getRadios(genreId));
    }
}

