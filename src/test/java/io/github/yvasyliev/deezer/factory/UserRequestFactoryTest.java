package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.NotificationResult;
import io.github.yvasyliev.deezer.model.Options;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.PermissionsResponse;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Radio;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.service.UserService;
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
    @Mock private TokenManager<AccessToken> accessTokenManager;

    @Test
    void testAddAlbumsWithVarargs() {
        var token = "test-token";
        var albumIds = List.of(123L, 456L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addAlbums("me", token, albumIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addAlbums(albumIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddAlbumsWithCollection() {
        var token = "test-token";
        var albumIds = List.of(123L, 456L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addAlbums("me", token, albumIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addAlbums(albumIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddAlbumsWithUserIdAndVarargs() {
        var userId = 123L;
        var token = "test-token";
        var albumIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addAlbums(String.valueOf(userId), token, albumIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addAlbums(userId, albumIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddAlbumsWithUserIdAndCollection() {
        var userId = 123L;
        var token = "test-token";
        var albumIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addAlbums(String.valueOf(userId), token, albumIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addAlbums(userId, albumIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddArtistsWithVarargs() {
        var token = "test-token";
        var artistIds = List.of(123L, 456L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addArtists("me", token, artistIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addArtists(artistIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddArtistsWithCollection() {
        var token = "test-token";
        var artistIds = List.of(123L, 456L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addArtists("me", token, artistIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addArtists(artistIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddArtistsWithUserIdAndVarargs() {
        var userId = 123L;
        var token = "test-token";
        var artistIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addArtists(String.valueOf(userId), token, artistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addArtists(userId, artistIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddArtistsWithUserIdAndCollection() {
        var userId = 123L;
        var token = "test-token";
        var artistIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addArtists(String.valueOf(userId), token, artistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addArtists(userId, artistIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddNotification() {
        var token = "test-token";
        var message = "test message";
        var expected = new NotificationResult(true);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addNotification("me", token, message)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.addNotification(message).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAddNotificationWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var message = "test message";
        var expected = new NotificationResult(true);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addNotification(String.valueOf(userId), token, message))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.addNotification(userId, message).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testAddPlaylistsWithVarargs() {
        var token = "test-token";
        var playlistIds = List.of(123L, 456L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addPlaylists("me", token, playlistIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPlaylists(playlistIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPlaylistsWithCollection() {
        var token = "test-token";
        var playlistIds = List.of(123L, 456L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addPlaylists("me", token, playlistIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPlaylists(playlistIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPlaylistsWithUserIdAndVarargs() {
        var userId = 123L;
        var token = "test-token";
        var playlistIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addPlaylists(String.valueOf(userId), token, playlistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPlaylists(userId, playlistIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPlaylistsWithUserIdAndCollection() {
        var userId = 123L;
        var token = "test-token";
        var playlistIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addPlaylists(String.valueOf(userId), token, playlistIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPlaylists(userId, playlistIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPodcast() {
        var podcastId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addPodcast("me", token, podcastId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPodcast(podcastId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddPodcastWithUserId() {
        var userId = 123L;
        var podcastId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addPodcast(String.valueOf(userId), token, podcastId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addPodcast(userId, podcastId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddRadio() {
        var radioId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addRadio("me", token, radioId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addRadio(radioId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddRadioWithUserId() {
        var userId = 123L;
        var radioId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addRadio(String.valueOf(userId), token, radioId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addRadio(userId, radioId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddTracksWithVarargs() {
        var token = "test-token";
        var trackIds = List.of(123L, 456L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addTracks("me", token, trackIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addTracks(trackIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddTracksWithCollection() {
        var token = "test-token";
        var trackIds = List.of(123L, 456L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addTracks("me", token, trackIds)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addTracks(trackIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddTracksWithUserIdAndVarargs() {
        var userId = 123L;
        var token = "test-token";
        var trackIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addTracks(String.valueOf(userId), token, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addTracks(userId, trackIds.toArray(Long[]::new)).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testAddTracksWithUserIdAndCollection() {
        var userId = 123L;
        var token = "test-token";
        var trackIds = List.of(456L, 789L);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.addTracks(String.valueOf(userId), token, trackIds))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.addTracks(userId, trackIds).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testCreatePlaylist() {
        var token = "test-token";
        var title = "My playlist";
        var expected = Playlist.builder().id(123L).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.createPlaylist("me", token, title, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.createPlaylist(title).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testCreatePlaylistWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var title = "My playlist";
        var expected = Playlist.builder().id(456L).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.createPlaylist(String.valueOf(userId), token, title, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.createPlaylist(userId, title).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testFollowUser() {
        var followeeId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.followUser("me", token, followeeId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.followUser(followeeId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testFollowUserWithUserId() {
        var userId = 123L;
        var followeeId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.followUser(String.valueOf(userId), token, followeeId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.followUser(userId, followeeId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testGetAlbumChar() {
        var token = "test-token";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getAlbumChart("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbumChar().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumChartWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getAlbumChart(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbumChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumRecommendations() {
        var token = "test-token";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getAlbumRecommendations("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbumRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAlbumRecommendationsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getAlbumRecommendations(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getAlbumRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistChart() {
        var token = "test-token";
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getArtistChart("me", token, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtistChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistChartWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getArtistChart(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtistChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistRecommendations() {
        var token = "test-token";
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getArtistRecommendations("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtistRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistRecommendationsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getArtistRecommendations(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtistRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtists() {
        var token = "test-token";
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getArtists("me", token, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtists().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetArtistsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Artist>builder()
                .data(Artist.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getArtists(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getArtists(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetChart() {
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getChart("me", token, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetChartWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getChart(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFlow() {
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getFlow("me", token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFlow().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFlowWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getFlow(String.valueOf(userId), token))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFlow(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFollowers() {
        var token = "test-token";
        var expected = Page.<User>builder()
                .data(User.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getFollowers("me", token, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFollowers().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFollowersWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<User>builder()
                .data(User.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getFollowers(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFollowers(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFollowings() {
        var token = "test-token";
        var expected = Page.<User>builder()
                .data(User.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getFollowings("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFollowings().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetFollowingsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<User>builder()
                .data(User.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getFollowings(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getFollowings(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetHistory() {
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getHistory("me", token, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getHistory().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetHistoryWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getHistory(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getHistory(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetOptions() {
        var token = "test-token";
        var expected = Options.builder().streaming(true).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getOptions("me", token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getOptions().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetOptionsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Options.builder().streaming(false).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getOptions(String.valueOf(userId), token))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getOptions(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPermissions() {
        var token = "test-token";
        var expected = new PermissionsResponse(PermissionsResponse.Permissions.builder().manageLibrary(true).build());

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPermissions("me", token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPermissions().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPermissionsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = new PermissionsResponse(PermissionsResponse.Permissions.builder().manageLibrary(false).build());

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPermissions(String.valueOf(userId), token))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPermissions(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPersonalSongs() {
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPersonalSongs("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPersonalSongs().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPersonalSongsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPersonalSongs(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPersonalSongs(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistChart() {
        var token = "test-token";
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPlaylistChart("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylistChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistChartWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPlaylistChart(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylistChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistRecommendations() {
        var token = "test-token";
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPlaylistRecommendations("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylistRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistRecommendationsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPlaylistRecommendations(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylistRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylists() {
        var token = "test-token";
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPlaylists("me", token, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylists().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPlaylistsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Playlist>builder()
                .data(Playlist.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getPlaylists(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getPlaylists(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadioRecommendations() {
        var token = "test-token";
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getRadioRecommendations("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getRadioRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadioRecommendationsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getRadioRecommendations(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getRadioRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadios() {
        var token = "test-token";
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getRadios("me", token, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getRadios().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRadiosWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Radio>builder()
                .data(Radio.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getRadios(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getRadios(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetReleaseRecommendations() {
        var token = "test-token";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getReleaseRecommendations("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getReleaseRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetReleaseRecommendationsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getReleaseRecommendations(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getReleaseRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackChart() {
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getTrackChart("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTrackChart().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackChartWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getTrackChart(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTrackChart(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackRecommendations() {
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getTrackRecommendations("me", token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTrackRecommendations().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTrackRecommendationsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getTrackRecommendations(String.valueOf(userId), token))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTrackRecommendations(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTracks() {
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getTracks("me", token, null, null)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTracks().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTracksWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Track>builder()
                .data(Track.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getTracks(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getTracks(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetUser() {
        var token = "test-token";
        var expected = User.builder().id(123L).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getUser("me", token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getUser().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetUserWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = User.builder().id(456L).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getUser(String.valueOf(userId), token))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getUser(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetUserAlbums() {
        var token = "test-token";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(123L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getUserAlbums("me", token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getUserAlbums().execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetUserAlbumsWithUserId() {
        var userId = 123L;
        var token = "test-token";
        var expected = Page.<Album>builder()
                .data(Album.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.getUserAlbums(String.valueOf(userId), token, null, null))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = userRequestFactory.getUserAlbums(userId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testRemoveAlbum() {
        var albumId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removeAlbum("me", token, albumId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeAlbum(albumId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveAlbumWithUserId() {
        var userId = 123L;
        var albumId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removeAlbum(String.valueOf(userId), token, albumId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeAlbum(userId, albumId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveArtist() {
        var artistId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removeArtist("me", token, artistId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeArtist(artistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveArtistWithUserId() {
        var userId = 123L;
        var artistId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removeArtist(String.valueOf(userId), token, artistId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeArtist(userId, artistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemovePlaylist() {
        var playlistId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removePlaylist("me", token, playlistId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removePlaylist(playlistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemovePlaylistWithUserId() {
        var userId = 123L;
        var playlistId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removePlaylist(String.valueOf(userId), token, playlistId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removePlaylist(userId, playlistId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemovePodcast() {
        var podcastId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removePodcast("me", token, podcastId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removePodcast(podcastId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemovePodcastWithUserId() {
        var userId = 123L;
        var podcastId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removePodcast(String.valueOf(userId), token, podcastId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removePodcast(userId, podcastId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveRadio() {
        var radioId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removeRadio("me", token, radioId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeRadio(radioId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveRadioWithUserId() {
        var userId = 123L;
        var radioId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removeRadio(String.valueOf(userId), token, radioId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeRadio(userId, radioId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveTrack() {
        var trackId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removeTrack("me", token, trackId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeTrack(trackId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testRemoveTrackWithUserId() {
        var userId = 123L;
        var trackId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.removeTrack(String.valueOf(userId), token, trackId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.removeTrack(userId, trackId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testUnfollowUser() {
        var followeeId = 123L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.unfollowUser("me", token, followeeId)).thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.unfollowUser(followeeId).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testUnfollowUserWithUserId() {
        var userId = 123L;
        var followeeId = 456L;
        var token = "test-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(userService.unfollowUser(String.valueOf(userId), token, followeeId))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = userRequestFactory.unfollowUser(userId, followeeId).execute();

        assertThat(actual).isTrue();
    }
}
