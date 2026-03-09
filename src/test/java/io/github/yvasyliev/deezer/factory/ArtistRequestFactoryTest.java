package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.service.ArtistService;
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
class ArtistRequestFactoryTest {
    @InjectMocks private ArtistRequestFactory artistRequestFactory;
    @Mock private ArtistService artistService;

    @Test
    void testGetAlbums() {
        var artistId = 123L;
        var index = 1;
        var limit = 10;
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(artistService.getAlbums(artistId, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getAlbums(artistId).index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetArtist() {
        var artistId = 123L;
        var expected = Artist.builder().id(artistId).build();

        when(artistService.getArtist(artistId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getArtist(artistId).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetFans() {
        var artistId = 123L;
        var index = 1;
        var limit = 10;
        var expected = Page.<User>builder()
                .data(User.builder().id(456L).build())
                .build();

        when(artistService.getFans(artistId, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getFans(artistId).index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetPlaylists() {
        var artistId = 123L;
        var index = 1;
        var limit = 10;
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(artistService.getPlaylists(artistId, index, limit)).thenReturn(
                CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getPlaylists(artistId).index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetRadio() {
        var artistId = 123L;
        var index = 1;
        var limit = 10;
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(artistService.getRadio(artistId, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getRadio(artistId).index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetRelated() {
        var artistId = 123L;
        var index = 1;
        var limit = 10;
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(artistService.getRelated(artistId, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getRelated(artistId).index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void testGetTop() {
        var artistId = 123L;
        var index = 1;
        var limit = 10;
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(artistService.getTop(artistId, index, limit)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = artistRequestFactory.getTop(artistId).index(index).limit(limit).executeAsync();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }
}
