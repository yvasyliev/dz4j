package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.deezer.model.OEmbed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

class OEmbedIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .build();
    }

    @Test
    void shouldReturnAlbumOEmbed() throws IOException {
        var albumId = 500138701L;
        var body = read("/response/oembed/get-album.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbed("https://www.deezer.com/album/" + albumId, body);

        assertEquals(expected, deezerClient.oEmbed().getAlbumOEmbed(albumId));
    }

    @Test
    void shouldReturnEpisodeOEmbed() throws IOException {
        var episodeId = 605632582L;
        var body = read("/response/oembed/get-episode.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbed("https://www.deezer.com/episode/" + episodeId, body);

        assertEquals(expected, deezerClient.oEmbed().getEpisodeOEmbed(episodeId));
    }

    @Test
    void shouldReturnPlaylistOEmbed() throws IOException {
        var playlistId = 11278651204L;
        var body = read("/response/oembed/get-playlist.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbed("https://www.deezer.com/playlist/" + playlistId, body);

        assertEquals(expected, deezerClient.oEmbed().getPlaylistOEmbed(playlistId));
    }

    @Test
    void shouldReturnShowOEmbed() throws IOException {
        var showId = 28142L;
        var body = read("/response/oembed/get-show.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbed("https://www.deezer.com/show/" + showId, body);

        assertEquals(expected, deezerClient.oEmbed().getShowOEmbed(showId));
    }

    @Test
    void shouldReturnTrackOEmbed() throws IOException {
        var trackId = 2646150812L;
        var body = read("/response/oembed/get-track.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbed("https://www.deezer.com/track/" + trackId, body);

        assertEquals(expected, deezerClient.oEmbed().getTrackOEmbed(trackId));
    }

    @Test
    void shouldReturnPageOEmbed() throws IOException {
        var pageId = "2gk4fY";
        var body = read("/response/oembed/get-page.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbed("https://deezer.page.link/" + pageId, body);

        assertEquals(expected, deezerClient.oEmbed().getPageOEmbed(pageId));
    }

    @Test
    void shouldReturnAlbumOEmbedWithOptions() throws IOException {
        var albumId = 500138701L;
        var autoplay = false;
        var maxWidth = 420;
        var maxHeight = 420;
        var radius = true;
        var tracklist = true;
        var body = read("/response/oembed/get-album.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbedWithOptions("https://www.deezer.com/album/" + albumId, autoplay, maxWidth, maxHeight, radius, tracklist, body);

        assertEquals(expected, deezerClient.oEmbed().getAlbumOEmbed(albumId)
                .autoplay(autoplay)
                .maxWidth(maxWidth)
                .maxHeight(maxHeight)
                .radius(radius)
                .tracklist(tracklist));
    }

    @Test
    void shouldReturnEpisodeOEmbedWithOptions() throws IOException {
        var episodeId = 605632582L;
        var autoplay = false;
        var maxWidth = 420;
        var maxHeight = 420;
        var radius = true;
        var tracklist = true;
        var body = read("/response/oembed/get-episode.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbedWithOptions("https://www.deezer.com/episode/" + episodeId, autoplay, maxWidth, maxHeight, radius, tracklist, body);

        assertEquals(expected, deezerClient.oEmbed().getEpisodeOEmbed(episodeId)
                .autoplay(autoplay)
                .maxWidth(maxWidth)
                .maxHeight(maxHeight)
                .radius(radius)
                .tracklist(tracklist));
    }

    @Test
    void shouldReturnPlaylistOEmbedWithOptions() throws IOException {
        var playlistId = 11278651204L;
        var autoplay = false;
        var maxWidth = 420;
        var maxHeight = 420;
        var radius = true;
        var tracklist = true;
        var body = read("/response/oembed/get-playlist.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbedWithOptions("https://www.deezer.com/playlist/" + playlistId, autoplay, maxWidth, maxHeight, radius, tracklist, body);

        assertEquals(expected, deezerClient.oEmbed().getPlaylistOEmbed(playlistId)
                .autoplay(autoplay)
                .maxWidth(maxWidth)
                .maxHeight(maxHeight)
                .radius(radius)
                .tracklist(tracklist));
    }

    @Test
    void shouldReturnShowOEmbedWithOptions() throws IOException {
        var showId = 28142L;
        var autoplay = false;
        var maxWidth = 420;
        var maxHeight = 420;
        var radius = true;
        var tracklist = true;
        var body = read("/response/oembed/get-show.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbedWithOptions("https://www.deezer.com/show/" + showId, autoplay, maxWidth, maxHeight, radius, tracklist, body);

        assertEquals(expected, deezerClient.oEmbed().getShowOEmbed(showId)
                .autoplay(autoplay)
                .maxWidth(maxWidth)
                .maxHeight(maxHeight)
                .radius(radius)
                .tracklist(tracklist));
    }

    @Test
    void shouldReturnTrackOEmbedWithOptions() throws IOException {
        var trackId = 2646150812L;
        var autoplay = false;
        var maxWidth = 420;
        var maxHeight = 420;
        var radius = true;
        var tracklist = true;
        var body = read("/response/oembed/get-track.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbedWithOptions("https://www.deezer.com/track/" + trackId, autoplay, maxWidth, maxHeight, radius, tracklist, body);

        assertEquals(expected, deezerClient.oEmbed().getTrackOEmbed(trackId)
                .autoplay(autoplay)
                .maxWidth(maxWidth)
                .maxHeight(maxHeight)
                .radius(radius)
                .tracklist(tracklist));
    }

    @Test
    void shouldReturnPageOEmbedWithOptions() throws IOException {
        var pageId = "2gk4fY";
        var autoplay = false;
        var maxWidth = 420;
        var maxHeight = 420;
        var radius = true;
        var tracklist = true;
        var body = read("/response/oembed/get-page.json");
        var expected = MAPPER.readValue(body, OEmbed.class);

        stubOEmbedWithOptions("https://deezer.page.link/" + pageId, autoplay, maxWidth, maxHeight, radius, tracklist, body);

        assertEquals(expected, deezerClient.oEmbed().getPageOEmbed(pageId)
                .autoplay(autoplay)
                .maxWidth(maxWidth)
                .maxHeight(maxHeight)
                .radius(radius)
                .tracklist(tracklist));
    }

    private void stubOEmbed(String url, String body) {
        stubFor(get(urlPathEqualTo("/oembed"))
                .withQueryParam("format", equalTo("json"))
                .withQueryParam("url", equalTo(url))
                .willReturn(okJson(body))
        );
    }

    private void stubOEmbedWithOptions(
            String url,
            boolean autoplay,
            int maxWidth,
            int maxHeight,
            boolean radius,
            boolean tracklist,
            String body
    ) {
        stubFor(get(urlPathEqualTo("/oembed"))
                .withQueryParam("format", equalTo("json"))
                .withQueryParam("url", equalTo(url))
                .withQueryParam("autoplay", equalTo(autoplay))
                .withQueryParam("maxwidth", equalTo(maxWidth))
                .withQueryParam("maxheight", equalTo(maxHeight))
                .withQueryParam("radius", equalTo(radius))
                .withQueryParam("tracklist", equalTo(tracklist))
                .willReturn(okJson(body))
        );
    }
}


