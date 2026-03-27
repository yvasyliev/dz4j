package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Album;
import io.github.yvasyliev.dz4j.model.Chart;
import io.github.yvasyliev.dz4j.model.Editorial;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.service.EditorialService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditorialRequestFactoryTest {
    @InjectMocks private EditorialRequestFactory editorialRequestFactory;
    @Mock private EditorialService editorialService;

    @Test
    void testGetEditorials() {
        var expected = Page.<Editorial>builder()
                .data(Editorial.builder().id(456L).build())
                .build();

        when(editorialService.getEditorials(null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorials().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetEditorial() {
        var genreId = 123L;
        var expected = Editorial.builder().id(genreId).build();

        when(editorialService.getEditorial(genreId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorial(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetEditorialSelection() {
        var genreId = 123L;
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(editorialService.getEditorialSelection(genreId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorialSelection(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetEditorialReleases() {
        var genreId = 123L;
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(editorialService.getEditorialReleases(genreId, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorialReleases(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetEditorialCharts() {
        var genreId = 123L;
        var expected = Page.<Chart>builder()
                .data(Chart.builder()
                        .tracks(Page.<Track>builder().data(Track.builder().id(456L).build()).build())
                        .build()
                )
                .build();

        when(editorialService.getEditorialCharts(genreId, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = editorialRequestFactory.getEditorialCharts(genreId).execute();

        assertEquals(expected, actual);
    }
}

