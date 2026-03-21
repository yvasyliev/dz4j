package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.AdvancedQuery;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Radio;
import io.github.yvasyliev.deezer.model.SimpleQuery;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.service.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchRequestFactoryTest {
    @InjectMocks private SearchRequestFactory searchRequestFactory;
    @Mock private SearchService searchService;

    @Test
    void testSearch() {
        var query = "test query";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(searchService.search(new SimpleQuery(query), null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.search(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAdvancedSearch() {
        var query = AdvancedQuery.builder().track("test track").build();
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(searchService.search(query, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.search(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testSearchAlbum() {
        var query = "test album";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(searchService.searchAlbum(new SimpleQuery(query), null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchAlbum(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAdvancedSearchAlbum() {
        var query = AdvancedQuery.builder().album("test album").build();
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(searchService.searchAlbum(query, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchAlbum(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testSearchArtist() {
        var query = "test artist";
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(123L).build())
                .build();

        when(searchService.searchArtist(new SimpleQuery(query), null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchArtist(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAdvancedSearchArtist() {
        var query = AdvancedQuery.builder().artist("test artist").build();
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(123L).build())
                .build();

        when(searchService.searchArtist(query, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchArtist(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testSearchPlaylist() {
        var query = "test playlist";
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(123L).build())
                .build();

        when(searchService.searchPlaylist(new SimpleQuery(query), null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchPlaylist(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAdvancedSearchPlaylist() {
        var query = AdvancedQuery.builder().track("test track").build();
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(123L).build())
                .build();

        when(searchService.searchPlaylist(query, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchPlaylist(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testSearchRadio() {
        var query = "test radio";
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(123L).build())
                .build();

        when(searchService.searchRadio(new SimpleQuery(query), null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchRadio(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAdvancedSearchRadio() {
        var query = AdvancedQuery.builder().artist("test artist").build();
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(123L).build())
                .build();

        when(searchService.searchRadio(query, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchRadio(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testSearchTrack() {
        var query = "test track";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(searchService.searchTrack(new SimpleQuery(query), null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchTrack(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAdvancedSearchTrack() {
        var query = AdvancedQuery.builder().track("test track").build();
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(searchService.searchTrack(query, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchTrack(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testSearchUser() {
        var query = "test user";
        var expected = Page.<User>builder()
                .data(User.builder().id(123L).build())
                .build();

        when(searchService.searchUser(new SimpleQuery(query), null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchUser(query).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAdvancedSearchUser() {
        var query = AdvancedQuery.builder().artist("test artist").build();
        var expected = Page.<User>builder()
                .data(User.builder().id(123L).build())
                .build();

        when(searchService.searchUser(query, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = searchRequestFactory.searchUser(query).execute();

        assertEquals(expected, actual);
    }
}

