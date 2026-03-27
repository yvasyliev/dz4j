package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.OEmbed;
import io.github.yvasyliev.dz4j.service.OEmbedService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OEmbedRequestFactoryTest {
    @InjectMocks private OEmbedRequestFactory oEmbedRequestFactory;
    @Mock private OEmbedService oEmbedService;

    @Test
    void testGetAlbumOEmbed() throws MalformedURLException {
        var albumId = 123L;
        var url = new URL("https://www.deezer.com/album/" + albumId);
        var expected = OEmbed.builder().id(456L).build();

        when(oEmbedService.getOEmbed(url, null, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = oEmbedRequestFactory.getAlbumOEmbed(albumId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetEpisodeOEmbed() throws MalformedURLException {
        var episodeId = 123L;
        var url = new URL("https://www.deezer.com/episode/" + episodeId);
        var expected = OEmbed.builder().id(456L).build();

        when(oEmbedService.getOEmbed(url, null, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = oEmbedRequestFactory.getEpisodeOEmbed(episodeId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistOEmbed() throws MalformedURLException {
        var playlistId = 123L;
        var url = new URL("https://www.deezer.com/playlist/" + playlistId);
        var expected = OEmbed.builder().id(456L).build();

        when(oEmbedService.getOEmbed(url, null, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = oEmbedRequestFactory.getPlaylistOEmbed(playlistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetShowOEmbed() throws MalformedURLException {
        var showId = 123L;
        var url = new URL("https://www.deezer.com/show/" + showId);
        var expected = OEmbed.builder().id(456L).build();

        when(oEmbedService.getOEmbed(url, null, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = oEmbedRequestFactory.getShowOEmbed(showId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackOEmbed() throws MalformedURLException {
        var trackId = 123L;
        var url = new URL("https://www.deezer.com/track/" + trackId);
        var expected = OEmbed.builder().id(456L).build();

        when(oEmbedService.getOEmbed(url, null, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = oEmbedRequestFactory.getTrackOEmbed(trackId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPageOEmbed() throws MalformedURLException {
        var pageId = "abc123";
        var url = new URL("https://deezer.page.link/" + pageId);
        var expected = OEmbed.builder().id(456L).build();

        when(oEmbedService.getOEmbed(url, null, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = oEmbedRequestFactory.getPageOEmbed(pageId).execute();

        assertEquals(expected, actual);
    }
}
