package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Artist;
import io.github.yvasyliev.dz4j.model.Genre;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Radio;
import io.github.yvasyliev.dz4j.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreRequestFactoryTest {
    @InjectMocks private GenreRequestFactory genreRequestFactory;
    @Mock private GenreService genreService;

    @Test
    void testGetArtists() {
        var genreId = 123L;
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(genreService.getArtists(genreId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = genreRequestFactory.getArtists(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetGenre() {
        var genreId = 123L;
        var expected = Genre.builder().id(genreId).build();

        when(genreService.getGenre(genreId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = genreRequestFactory.getGenre(genreId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetGenres() {
        var expected = Page.<Genre>builder()
                .data(Genre.builder().id(123L).build())
                .build();

        when(genreService.getGenres()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = genreRequestFactory.getGenres().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadios() {
        var genreId = 123L;
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(456L).build())
                .build();

        when(genreService.getRadios(genreId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = genreRequestFactory.getRadios(genreId).execute();

        assertEquals(expected, actual);
    }
}
