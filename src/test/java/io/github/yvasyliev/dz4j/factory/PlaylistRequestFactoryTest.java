package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.TokenManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Playlist;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.model.User;
import io.github.yvasyliev.dz4j.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaylistRequestFactoryTest {
    @InjectMocks private PlaylistRequestFactory playlistRequestFactory;
    @Mock private PlaylistService playlistService;
    @Mock private TokenManager<AccessToken> accessTokenManager;

    @Test
    void testAddTracksWithCollection() {
        var playlistId = 123L;
        var token = "test-token";
        var trackIds = List.of(456L, 789L);
        var expected = true;

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.addTracks(playlistId, token, trackIds))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = playlistRequestFactory.addTracks(playlistId, trackIds).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAddTracksWithVarargs() {
        var playlistId = 123L;
        var token = "test-token";
        var trackIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.addTracks(playlistId, token, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = playlistRequestFactory.addTracks(playlistId, trackIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testDeletePlaylist() {
        var playlistId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.deletePlaylist(playlistId, token)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = playlistRequestFactory.deletePlaylist(playlistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testDeleteTracksWithCollection() {
        var playlistId = 123L;
        var token = "test-token";
        var trackIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.deleteTracks(playlistId, token, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = playlistRequestFactory.deleteTracks(playlistId, trackIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testDeleteTracksWithVarargs() {
        var playlistId = 123L;
        var token = "test-token";
        var trackIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.deleteTracks(playlistId, token, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = playlistRequestFactory.deleteTracks(playlistId, trackIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testGetFans() {
        var playlistId = 123L;
        var expected = Page.<User>builder()
                .data(User.builder().id(456L).build())
                .build();

        when(playlistService.getFans(playlistId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = playlistRequestFactory.getFans(playlistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylist() {
        var playlistId = 123L;
        var expected = Playlist.builder().id(playlistId).build();

        when(playlistService.getPlaylist(playlistId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = playlistRequestFactory.getPlaylist(playlistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadio() {
        var playlistId = 123L;
        var expected = Optional.of(Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build());

        when(playlistService.getRadio(playlistId, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = playlistRequestFactory.getRadio(playlistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTracks() {
        var playlistId = 123L;
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(playlistService.getTracks(playlistId, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = playlistRequestFactory.getTracks(playlistId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testMarkAsSeen() {
        var playlistId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.markAsSeen(playlistId, token)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = playlistRequestFactory.markAsSeen(playlistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testOrderTracksWithCollection() {
        var playlistId = 123L;
        var token = "test-token";
        var trackIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.orderTracks(playlistId, token, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = playlistRequestFactory.orderTracks(playlistId, trackIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testOrderTracksWithVarargs() {
        var playlistId = 123L;
        var token = "test-token";
        var trackIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.orderTracks(playlistId, token, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = playlistRequestFactory.orderTracks(playlistId, trackIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testUpdatePlaylist() {
        var playlistId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(playlistService.updatePlaylist(playlistId, token, null, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = playlistRequestFactory.updatePlaylist(playlistId).execute();

        assertThat(actual).isTrue();
    }
}
