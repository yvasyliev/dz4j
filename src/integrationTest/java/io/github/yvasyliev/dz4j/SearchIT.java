package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.model.AdvancedQuery;
import io.github.yvasyliev.dz4j.model.Album;
import io.github.yvasyliev.dz4j.model.Artist;
import io.github.yvasyliev.dz4j.model.Order;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Playlist;
import io.github.yvasyliev.dz4j.model.Radio;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class SearchIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .build();
    }

    @Test
    void shouldSearch() throws IOException {
        var query = "eminem";
        var body = read("/response/search/search.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/search"))
                .withQueryParam("q", equalTo(query))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.search().search(query));
    }

    @Test
    void shouldAdvancedSearch() throws IOException {
        var query = AdvancedQuery.builder().track("Lose Yourself").build();
        var expandedQuery = "track:\"Lose Yourself\"";
        var index = 5;
        var limit = 10;
        var order = Order.DURATION_DESC;
        var body = read("/response/search/search.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/search"))
                .withQueryParam("q", equalTo(expandedQuery))
                .withQueryParam("strict", equalTo("on"))
                .withQueryParam("order", equalTo(order))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(
                expected,
                deezerClient.search().search(query).strict(true).order(order).index(index).limit(limit)
        );
    }

    @Test
    void shouldSearchAlbum() throws IOException {
        var query = "eminem";
        var body = read("/response/search/search-album.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/search/album"))
                .withQueryParam("q", equalTo(query))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.search().searchAlbum(query));
    }

    @Test
    void shouldAdvancedSearchAlbum() throws IOException {
        var query = AdvancedQuery.builder().album("Curtain Call: The Hits").build();
        var expandedQuery = "album:\"Curtain Call: The Hits\"";
        var index = 5;
        var limit = 10;
        var order = Order.DURATION_DESC;
        var body = read("/response/search/search-album.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/search/album"))
                .withQueryParam("q", equalTo(expandedQuery))
                .withQueryParam("strict", equalTo("on"))
                .withQueryParam("order", equalTo(order))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(
                expected,
                deezerClient.search().searchAlbum(query).strict(true).order(order).index(index).limit(limit)
        );
    }

    @Test
    void shouldSearchArtist() throws IOException {
        var query = "eminem";
        var body = read("/response/search/search-artist.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/search/artist"))
                .withQueryParam("q", equalTo(query))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.search().searchArtist(query));
    }

    @Test
    void shouldAdvancedSearchArtist() throws IOException {
        var query = AdvancedQuery.builder().artist("Eminem").build();
        var expandedQuery = "artist:\"Eminem\"";
        var index = 5;
        var limit = 10;
        var order = Order.DURATION_DESC;
        var body = read("/response/search/search-artist.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/search/artist"))
                .withQueryParam("q", equalTo(expandedQuery))
                .withQueryParam("strict", equalTo("on"))
                .withQueryParam("order", equalTo(order))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(
                expected,
                deezerClient.search().searchArtist(query).strict(true).order(order).index(index).limit(limit)
        );
    }

    @Test
    void shouldSearchPlaylist() throws IOException {
        var query = "eminem";
        var body = read("/response/search/search-playlist.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/search/playlist"))
                .withQueryParam("q", equalTo(query))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.search().searchPlaylist(query));
    }

    @Test
    void shouldAdvancedSearchPlaylist() throws IOException {
        var query = AdvancedQuery.builder().track("Lose Yourself").build();
        var expandedQuery = "track:\"Lose Yourself\"";
        var index = 5;
        var limit = 10;
        var order = Order.DURATION_DESC;
        var body = read("/response/search/search-playlist.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/search/playlist"))
                .withQueryParam("q", equalTo(expandedQuery))
                .withQueryParam("strict", equalTo("on"))
                .withQueryParam("order", equalTo(order))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(
                expected,
                deezerClient.search().searchPlaylist(query).strict(true).order(order).index(index).limit(limit)
        );
    }

    @Test
    void shouldSearchRadio() throws IOException {
        var query = "80s";
        var body = read("/response/search/search-radio.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/search/radio"))
                .withQueryParam("q", equalTo(query))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.search().searchRadio(query));
    }

    @Test
    void shouldAdvancedSearchRadio() throws IOException {
        var query = AdvancedQuery.builder().artist("The '80s").build();
        var expandedQuery = "artist:\"The '80s\"";
        var index = 5;
        var limit = 10;
        var order = Order.DURATION_DESC;
        var body = read("/response/search/search-radio.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/search/radio"))
                .withQueryParam("q", equalTo(expandedQuery))
                .withQueryParam("strict", equalTo("on"))
                .withQueryParam("order", equalTo(order))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(
                expected,
                deezerClient.search().searchRadio(query).strict(true).order(order).index(index).limit(limit)
        );
    }

    @Test
    void shouldSearchTrack() throws IOException {
        var query = "eminem";
        var body = read("/response/search/search-track.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/search/track"))
                .withQueryParam("q", equalTo(query))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.search().searchTrack(query));
    }

    @Test
    void shouldAdvancedSearchTrack() throws IOException {
        var query = AdvancedQuery.builder().track("Lose Yourself").build();
        var expandedQuery = "track:\"Lose Yourself\"";
        var index = 5;
        var limit = 10;
        var order = Order.DURATION_DESC;
        var body = read("/response/search/search-track.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/search/track"))
                .withQueryParam("q", equalTo(expandedQuery))
                .withQueryParam("strict", equalTo("on"))
                .withQueryParam("order", equalTo(order))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(
                expected,
                deezerClient.search().searchTrack(query).strict(true).order(order).index(index).limit(limit)
        );
    }

    @Test
    void shouldSearchUser() throws IOException {
        var query = "eminem";
        var body = read("/response/search/search-user.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/search/user"))
                .withQueryParam("q", equalTo(query))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.search().searchUser(query));
    }

    @Test
    void shouldAdvancedSearchUser() throws IOException {
        var query = AdvancedQuery.builder().artist("Eminem").build();
        var expandedQuery = "artist:\"Eminem\"";
        var index = 5;
        var limit = 10;
        var order = Order.DURATION_DESC;
        var body = read("/response/search/search-user.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/search/user"))
                .withQueryParam("q", equalTo(expandedQuery))
                .withQueryParam("strict", equalTo("on"))
                .withQueryParam("order", equalTo(order))
                .withQueryParam("index", equalTo(index))
                .withQueryParam("limit", equalTo(limit))
                .willReturn(okJson(body))
        );

        assertEquals(
                expected,
                deezerClient.search().searchUser(query).strict(true).order(order).index(index).limit(limit)
        );
    }
}
