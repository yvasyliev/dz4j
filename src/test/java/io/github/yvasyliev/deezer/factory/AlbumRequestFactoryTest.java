package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumRequestFactoryTest {
    @InjectMocks private AlbumRequestFactory albumRequestFactory;
    @Mock private AlbumService albumService;

    @Test
    void testGetAlbum() {
        var albumId = 123L;
        var expected = Album.builder().id(albumId).build();

        when(albumService.getAlbum(albumId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = albumRequestFactory.getAlbum(albumId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumFans() {
        var albumId = 123L;
        var expected = Page.<User>builder()
                .data(User.builder().id(456L).build())
                .build();

        when(albumService.getAlbumFans(albumId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = albumRequestFactory.getAlbumFans(albumId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumTracks() {
        var albumId = 123L;
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(albumService.getAlbumTracks(albumId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = albumRequestFactory.getAlbumTracks(albumId).execute();

        assertEquals(expected, actual);
    }
}
