package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Chart;
import io.github.yvasyliev.deezer.model.Editorial;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.service.EditorialService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditorialRequestFactoryTest {
    @InjectMocks private EditorialRequestFactory editorialRequestFactory;
    @Mock private EditorialService editorialService;

    @Test
    void testGetEditorials() {
        var index = 1;
        var limit = 10;
        var expected = Page.<Editorial>builder()
                .data(Editorial.builder().id(456L).build())
                .build();

        when(editorialService.getEditorials(index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorials().index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetEditorial() {
        var genreId = 123L;
        var expected = Editorial.builder().id(genreId).build();

        when(editorialService.getEditorial(genreId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorial(genreId).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetEditorialSelection() {
        var genreId = 123L;
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(editorialService.getEditorialSelection(genreId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorialSelection(genreId).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetEditorialReleases() {
        var genreId = 123L;
        var index = 1;
        var limit = 10;
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(editorialService.getEditorialReleases(genreId, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorialReleases(genreId).index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetEditorialCharts() {
        var genreId = 123L;
        var index = 1;
        var limit = 10;
        var expected = Page.<Chart>builder()
                .data(Chart.builder()
                        .tracks(Page.<Track>builder().data(Track.builder().id(456L).build()).build())
                        .build()
                )
                .build();

        when(editorialService.getEditorialCharts(genreId, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorialCharts(genreId).index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }
}

