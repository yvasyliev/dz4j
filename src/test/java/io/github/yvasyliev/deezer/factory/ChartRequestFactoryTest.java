package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Chart;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Podcast;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.service.ChartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChartRequestFactoryTest {
    @InjectMocks private ChartRequestFactory chartRequestFactory;
    @Mock private ChartService chartService;

    @Test
    void testGetAlbumsChart() {
        var genreId = 123L;
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(chartService.getAlbumsChart(genreId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = chartRequestFactory.getAlbumsChart(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistsChart() {
        var genreId = 123L;
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(chartService.getArtistsChart(genreId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = chartRequestFactory.getArtistsChart(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetChart() {
        var genreId = 123L;
        var expected = Chart.builder()
                .tracks(Page.<Track>builder().data(Track.builder().id(456L).build()).build())
                .build();

        when(chartService.getChart(genreId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = chartRequestFactory.getChart(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistsChart() {
        var genreId = 123L;
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(chartService.getPlaylistsChart(genreId, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = chartRequestFactory.getPlaylistsChart(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPodcastsChart() {
        var genreId = 123L;
        var expected = Page.<Podcast>builder()
                .data(Podcast.builder().id(456L).build())
                .build();

        when(chartService.getPodcastsChart(genreId, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = chartRequestFactory.getPodcastsChart(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTracksChart() {
        var genreId = 123L;
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(chartService.getTracksChart(genreId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = chartRequestFactory.getTracksChart(genreId).execute();

        assertEquals(expected, actual);
    }
}

