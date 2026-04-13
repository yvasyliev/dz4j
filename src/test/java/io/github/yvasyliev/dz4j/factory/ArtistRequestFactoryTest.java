package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Album;
import io.github.yvasyliev.dz4j.model.Artist;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Playlist;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.model.User;
import io.github.yvasyliev.dz4j.service.ArtistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistRequestFactoryTest {
    @InjectMocks private ArtistRequestFactory artistRequestFactory;
    @Mock private ArtistService artistService;

    @Test
    void testGetAlbums() {
        var artistId = 123L;
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(artistService.getAlbums(artistId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getAlbums(artistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtist() {
        var artistId = 123L;
        var expected = Artist.builder().id(artistId).build();

        when(artistService.getArtist(artistId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getArtist(artistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFans() {
        var artistId = 123L;
        var expected = Page.<User>builder()
                .data(User.builder().id(456L).build())
                .build();

        when(artistService.getFans(artistId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getFans(artistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylists() {
        var artistId = 123L;
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(artistService.getPlaylists(artistId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getPlaylists(artistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadio() {
        var artistId = 123L;
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(artistService.getRadio(artistId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getRadio(artistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRelated() {
        var artistId = 123L;
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(artistService.getRelated(artistId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getRelated(artistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTop() {
        var artistId = 123L;
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(artistService.getTop(artistId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getTop(artistId).execute();

        assertEquals(expected, actual);
    }
}
