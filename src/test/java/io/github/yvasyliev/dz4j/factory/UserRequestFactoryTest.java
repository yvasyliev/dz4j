package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Album;
import io.github.yvasyliev.dz4j.model.Artist;
import io.github.yvasyliev.dz4j.model.NotificationResult;
import io.github.yvasyliev.dz4j.model.Options;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.PermissionsResponse;
import io.github.yvasyliev.dz4j.model.Playlist;
import io.github.yvasyliev.dz4j.model.Radio;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.model.User;
import io.github.yvasyliev.dz4j.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRequestFactoryTest {
    @InjectMocks private UserRequestFactory userRequestFactory;
    @Mock private UserService userService;
    @Mock private AuthorizationManager authorizationManager;

    @Test
    void testAddAlbumsWithVarargs() {
        var accessToken = new AccessToken("test-token");
        var albumIds = List.of(123L, 456L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addAlbums("me", accessToken, albumIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addAlbums(albumIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddAlbumsWithCollection() {
        var accessToken = new AccessToken("test-token");
        var albumIds = List.of(123L, 456L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addAlbums("me", accessToken, albumIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addAlbums(albumIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddAlbumsWithUserIdAndVarargs() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var albumIds = List.of(456L, 789L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addAlbums(String.valueOf(userId), accessToken, albumIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addAlbums(userId, albumIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddAlbumsWithUserIdAndCollection() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var albumIds = List.of(456L, 789L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addAlbums(String.valueOf(userId), accessToken, albumIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addAlbums(userId, albumIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddArtistsWithVarargs() {
        var accessToken = new AccessToken("test-token");
        var artistIds = List.of(123L, 456L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addArtists("me", accessToken, artistIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addArtists(artistIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddArtistsWithCollection() {
        var accessToken = new AccessToken("test-token");
        var artistIds = List.of(123L, 456L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addArtists("me", accessToken, artistIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addArtists(artistIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddArtistsWithUserIdAndVarargs() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var artistIds = List.of(456L, 789L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addArtists(String.valueOf(userId), accessToken, artistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addArtists(userId, artistIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddArtistsWithUserIdAndCollection() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var artistIds = List.of(456L, 789L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addArtists(String.valueOf(userId), accessToken, artistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addArtists(userId, artistIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddNotification() {
        var accessToken = new AccessToken("test-token");
        var message = "test message";
        var expected = new NotificationResult(true);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addNotification("me", accessToken, message))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.addNotification(message).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAddNotificationWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var message = "test message";
        var expected = new NotificationResult(true);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addNotification(String.valueOf(userId), accessToken, message))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.addNotification(userId, message).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAddPlaylistsWithVarargs() {
        var accessToken = new AccessToken("test-token");
        var playlistIds = List.of(123L, 456L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addPlaylists("me", accessToken, playlistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPlaylists(playlistIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPlaylistsWithCollection() {
        var accessToken = new AccessToken("test-token");
        var playlistIds = List.of(123L, 456L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addPlaylists("me", accessToken, playlistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPlaylists(playlistIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPlaylistsWithUserIdAndVarargs() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var playlistIds = List.of(456L, 789L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addPlaylists(String.valueOf(userId), accessToken, playlistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPlaylists(userId, playlistIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPlaylistsWithUserIdAndCollection() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var playlistIds = List.of(456L, 789L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addPlaylists(String.valueOf(userId), accessToken, playlistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPlaylists(userId, playlistIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPodcast() {
        var podcastId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addPodcast("me", accessToken, podcastId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPodcast(podcastId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPodcastWithUserId() {
        var userId = 123L;
        var podcastId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addPodcast(String.valueOf(userId), accessToken, podcastId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPodcast(userId, podcastId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddRadio() {
        var radioId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addRadio("me", accessToken, radioId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addRadio(radioId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddRadioWithUserId() {
        var userId = 123L;
        var radioId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addRadio(String.valueOf(userId), accessToken, radioId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addRadio(userId, radioId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddTracksWithVarargs() {
        var accessToken = new AccessToken("test-token");
        var trackIds = List.of(123L, 456L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addTracks("me", accessToken, trackIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addTracks(trackIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddTracksWithCollection() {
        var accessToken = new AccessToken("test-token");
        var trackIds = List.of(123L, 456L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addTracks("me", accessToken, trackIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addTracks(trackIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddTracksWithUserIdAndVarargs() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var trackIds = List.of(456L, 789L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addTracks(String.valueOf(userId), accessToken, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addTracks(userId, trackIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddTracksWithUserIdAndCollection() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var trackIds = List.of(456L, 789L);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.addTracks(String.valueOf(userId), accessToken, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addTracks(userId, trackIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testCreatePlaylist() {
        var accessToken = new AccessToken("test-token");
        var title = "My playlist";
        var expected = Playlist.builder().id(123L).build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.createPlaylist("me", accessToken, title, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.createPlaylist(title).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testCreatePlaylistWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var title = "My playlist";
        var expected = Playlist.builder().id(456L).build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.createPlaylist(String.valueOf(userId), accessToken, title, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.createPlaylist(userId, title).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testFollowUser() {
        var followeeId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.followUser("me", accessToken, followeeId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.followUser(followeeId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testFollowUserWithUserId() {
        var userId = 123L;
        var followeeId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.followUser(String.valueOf(userId), accessToken, followeeId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.followUser(userId, followeeId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testGetAlbumChart() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getAlbumChart("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbumChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumChartWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getAlbumChart(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbumChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumRecommendations() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getAlbumRecommendations("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbumRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumRecommendationsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getAlbumRecommendations(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbumRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistChart() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getArtistChart("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtistChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistChartWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getArtistChart(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtistChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistRecommendations() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getArtistRecommendations("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtistRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistRecommendationsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getArtistRecommendations(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtistRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtists() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getArtists("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtists().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getArtists(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtists(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetChart() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getChart("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetChartWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getChart(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFlow() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getFlow("me", accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFlow().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFlowWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getFlow(String.valueOf(userId), accessToken))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFlow(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFollowers() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<User>builder()
                .data(User.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getFollowers("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFollowers().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFollowersWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<User>builder()
                .data(User.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getFollowers(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFollowers(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFollowings() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<User>builder()
                .data(User.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getFollowings("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFollowings().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFollowingsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<User>builder()
                .data(User.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getFollowings(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFollowings(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetHistory() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getHistory("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getHistory().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetHistoryWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getHistory(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getHistory(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetOptions() {
        var accessToken = new AccessToken("test-token");
        var expected = Options.builder().streaming(true).build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getOptions("me", accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getOptions().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetOptionsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Options.builder().streaming(false).build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getOptions(String.valueOf(userId), accessToken))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getOptions(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPermissions() {
        var accessToken = new AccessToken("test-token");
        var expected = new PermissionsResponse(PermissionsResponse.Permissions.builder().manageLibrary(true).build());

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPermissions("me", accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPermissions().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPermissionsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = new PermissionsResponse(PermissionsResponse.Permissions.builder().manageLibrary(false).build());

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPermissions(String.valueOf(userId), accessToken))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPermissions(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPersonalSongs() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPersonalSongs("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPersonalSongs().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPersonalSongsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPersonalSongs(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPersonalSongs(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistChart() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPlaylistChart("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylistChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistChartWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPlaylistChart(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylistChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistRecommendations() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPlaylistRecommendations("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylistRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistRecommendationsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPlaylistRecommendations(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylistRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylists() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPlaylists("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylists().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getPlaylists(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylists(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadioRecommendations() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getRadioRecommendations("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getRadioRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadioRecommendationsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getRadioRecommendations(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getRadioRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadios() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getRadios("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getRadios().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadiosWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getRadios(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getRadios(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetReleaseRecommendations() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getReleaseRecommendations("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getReleaseRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetReleaseRecommendationsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getReleaseRecommendations(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getReleaseRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackChart() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getTrackChart("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTrackChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackChartWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getTrackChart(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTrackChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackRecommendations() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getTrackRecommendations("me", accessToken))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTrackRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackRecommendationsWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getTrackRecommendations(String.valueOf(userId), accessToken))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTrackRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTracks() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getTracks("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTracks().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTracksWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getTracks(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTracks(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetUser() {
        var accessToken = new AccessToken("test-token");
        var expected = User.builder().id(123L).build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getUser("me", accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getUser().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetUserWithUserId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = User.builder().id(456L).build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getUser(String.valueOf(userId), accessToken))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getUser(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbums() {
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getAlbums("me", accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbums().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumsWithId() {
        var userId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.getAlbums(String.valueOf(userId), accessToken, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbums(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testRemoveAlbum() {
        var albumId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removeAlbum("me", accessToken, albumId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeAlbum(albumId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveAlbumWithUserId() {
        var userId = 123L;
        var albumId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removeAlbum(String.valueOf(userId), accessToken, albumId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeAlbum(userId, albumId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveArtist() {
        var artistId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removeArtist("me", accessToken, artistId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeArtist(artistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveArtistWithUserId() {
        var userId = 123L;
        var artistId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removeArtist(String.valueOf(userId), accessToken, artistId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeArtist(userId, artistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemovePlaylist() {
        var playlistId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removePlaylist("me", accessToken, playlistId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removePlaylist(playlistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemovePlaylistWithUserId() {
        var userId = 123L;
        var playlistId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removePlaylist(String.valueOf(userId), accessToken, playlistId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removePlaylist(userId, playlistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemovePodcast() {
        var podcastId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removePodcast("me", accessToken, podcastId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removePodcast(podcastId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemovePodcastWithUserId() {
        var userId = 123L;
        var podcastId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removePodcast(String.valueOf(userId), accessToken, podcastId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removePodcast(userId, podcastId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveRadio() {
        var radioId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removeRadio("me", accessToken, radioId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeRadio(radioId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveRadioWithUserId() {
        var userId = 123L;
        var radioId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removeRadio(String.valueOf(userId), accessToken, radioId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeRadio(userId, radioId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveTrack() {
        var trackId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removeTrack("me", accessToken, trackId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeTrack(trackId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveTrackWithUserId() {
        var userId = 123L;
        var trackId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.removeTrack(String.valueOf(userId), accessToken, trackId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeTrack(userId, trackId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testUnfollowUser() {
        var followeeId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.unfollowUser("me", accessToken, followeeId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.unfollowUser(followeeId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testUnfollowUserWithUserId() {
        var userId = 123L;
        var followeeId = 456L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.unfollowUser(String.valueOf(userId), accessToken, followeeId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.unfollowUser(userId, followeeId).execute();

        assertThat(actual).isTrue();
    }
}
