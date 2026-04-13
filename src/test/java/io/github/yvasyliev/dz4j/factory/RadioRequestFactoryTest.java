package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Genre;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Radio;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.service.RadioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RadioRequestFactoryTest {
    @InjectMocks private RadioRequestFactory radioRequestFactory;
    @Mock private RadioService radioService;

    @Test
    void testGetGenres() {
        var expected = Page.<Genre>builder()
                .data(Genre.builder().id(123L).build())
                .build();

        when(radioService.getGenres()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = radioRequestFactory.getGenres().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetLists() {
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(123L).build())
                .build();

        when(radioService.getLists(null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = radioRequestFactory.getLists().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadio() {
        var radioId = 123L;
        var expected = Radio.builder().id(radioId).build();

        when(radioService.getRadio(radioId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = radioRequestFactory.getRadio(radioId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadios() {
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(123L).build())
                .build();

        when(radioService.getRadios()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = radioRequestFactory.getRadios().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTracks() {
        var radioId = 123L;
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(radioService.getTracks(radioId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = radioRequestFactory.getTracks(radioId).execute();

        assertEquals(expected, actual);
    }
}
