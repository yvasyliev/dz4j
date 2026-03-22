package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class UserIT extends AbstractIT {
    private static final long USER_ID = 2208L;

    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .authorization(ACCESS_TOKEN)
                .build();
    }

    @Test
    void shouldAddAlbumsForMe() throws IOException {
        var albumIds = new Long[]{1L, 2L, 3L};
        var body = read("/response/user/add-albums.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/albums"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("album_ids", equalTo("1,2,3"))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addAlbums(albumIds));
        assertEquals(expected, deezerClient.user().addAlbums(Arrays.asList(albumIds)));
    }

    @Test
    void shouldAddAlbums() throws IOException {
        var albumIds = new Long[]{1L, 2L, 3L};
        var body = read("/response/user/add-albums.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/albums"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("album_ids", equalTo("1,2,3"))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addAlbums(USER_ID, albumIds));
        assertEquals(expected, deezerClient.user().addAlbums(USER_ID, Arrays.asList(albumIds)));
    }

    @Test
    void shouldAddArtistsForMe() throws IOException {
        var artistIds = new Long[]{1L, 2L, 3L};
        var body = read("/response/user/add-artists.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/artists"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("artist_ids", equalTo("1,2,3"))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addArtists(artistIds));
        assertEquals(expected, deezerClient.user().addArtists(Arrays.asList(artistIds)));
    }

    @Test
    void shouldAddArtists() throws IOException {
        var artistIds = new Long[]{1L, 2L, 3L};
        var body = read("/response/user/add-artists.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/artists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("artist_ids", equalTo("1,2,3"))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addArtists(USER_ID, artistIds));
        assertEquals(expected, deezerClient.user().addArtists(USER_ID, Arrays.asList(artistIds)));
    }

    @Test
    void shouldAddNotificationForMe() throws IOException {
        var message = "Hello Deezer";
        var body = read("/response/user/add-notification.json");
        var expected = MAPPER.readValue(body, NotificationResult.class);

        stubFor(post(urlPathTemplate("/user/{userId}/notifications"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("message", equalTo(message))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addNotification(message));
    }

    @Test
    void shouldAddNotification() throws IOException {
        var message = "Hello Deezer";
        var body = read("/response/user/add-notification.json");
        var expected = MAPPER.readValue(body, NotificationResult.class);

        stubFor(post(urlPathTemplate("/user/{userId}/notifications"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("message", equalTo(message))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addNotification(USER_ID, message));
    }

    @Test
    void shouldAddPlaylistsForMe() throws IOException {
        var playlistIds = new Long[]{1L, 2L, 3L};
        var body = read("/response/user/add-playlists.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/playlists"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("playlist_ids", equalTo("1,2,3"))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addPlaylists(playlistIds));
        assertEquals(expected, deezerClient.user().addPlaylists(Arrays.asList(playlistIds)));
    }

    @Test
    void shouldAddPlaylists() throws IOException {
        var playlistIds = new Long[]{1L, 2L, 3L};
        var body = read("/response/user/add-playlists.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/playlists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("playlist_ids", equalTo("1,2,3"))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addPlaylists(USER_ID, playlistIds));
        assertEquals(expected, deezerClient.user().addPlaylists(USER_ID, Arrays.asList(playlistIds)));
    }

    @Test
    void shouldAddPodcastForMe() throws IOException {
        var podcastId = 10L;
        var body = read("/response/user/add-podcast.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/podcasts"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("podcast_id", equalTo(podcastId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addPodcast(podcastId));
    }

    @Test
    void shouldAddPodcast() throws IOException {
        var podcastId = 10L;
        var body = read("/response/user/add-podcast.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/podcasts"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("podcast_id", equalTo(podcastId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addPodcast(USER_ID, podcastId));
    }

    @Test
    void shouldAddRadioForMe() throws IOException {
        var radioId = 10L;
        var body = read("/response/user/add-radio.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/radios"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("radio_id", equalTo(radioId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addRadio(radioId));
    }

    @Test
    void shouldAddRadio() throws IOException {
        var radioId = 10L;
        var body = read("/response/user/add-radio.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/radios"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("radio_id", equalTo(radioId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addRadio(USER_ID, radioId));
    }

    @Test
    void shouldAddTracksForMe() throws IOException {
        var trackIds = new Long[]{1L, 2L, 3L};
        var body = read("/response/user/add-tracks.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/tracks"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("track_ids", equalTo("1,2,3"))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addTracks(trackIds));
        assertEquals(expected, deezerClient.user().addTracks(Arrays.asList(trackIds)));
    }

    @Test
    void shouldAddTracks() throws IOException {
        var trackIds = new Long[]{1L, 2L, 3L};
        var body = read("/response/user/add-tracks.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/tracks"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("track_ids", equalTo("1,2,3"))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().addTracks(USER_ID, trackIds));
        assertEquals(expected, deezerClient.user().addTracks(USER_ID, Arrays.asList(trackIds)));
    }

    @Test
    void shouldCreatePlaylistForMe() throws IOException {
        var title = "My playlist";
        var body = read("/response/user/create-playlist.json");
        var expected = MAPPER.readValue(body, Playlist.class);

        stubFor(post(urlPathTemplate("/user/{userId}/playlists"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("title", equalTo(title))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().createPlaylist(title));
    }

    @Test
    void shouldCreatePlaylist() throws IOException {
        var title = "My playlist";
        var body = read("/response/user/create-playlist.json");
        var expected = MAPPER.readValue(body, Playlist.class);

        stubFor(post(urlPathTemplate("/user/{userId}/playlists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("title", equalTo(title))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().createPlaylist(USER_ID, title));
    }

    @Test
    void shouldFollowUserForMe() throws IOException {
        var followeeId = 15L;
        var body = read("/response/user/follow-user.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/followings"))
                .withPathParam("userId", equalTo("me"))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("user_id", equalTo(followeeId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().followUser(followeeId));
    }

    @Test
    void shouldFollowUser() throws IOException {
        var followeeId = 15L;
        var body = read("/response/user/follow-user.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(post(urlPathTemplate("/user/{userId}/followings"))
                .withPathParam("userId", equalTo(USER_ID))
                .withFormParam("access_token", equalTo(ACCESS_TOKEN))
                .withFormParam("user_id", equalTo(followeeId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().followUser(USER_ID, followeeId));
    }

    @Test
    void shouldReturnAlbumChartForMe() throws IOException {
        var body = read("/response/user/get-album-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts/albums"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getAlbumChart());
    }

    @Test
    void shouldReturnAlbumChart() throws IOException {
        var body = read("/response/user/get-album-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts/albums"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getAlbumChart(USER_ID));
    }

    @Test
    void shouldReturnAlbumRecommendationsForMe() throws IOException {
        var body = read("/response/user/get-album-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/albums"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getAlbumRecommendations());
    }

    @Test
    void shouldReturnAlbumRecommendations() throws IOException {
        var body = read("/response/user/get-album-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/albums"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getAlbumRecommendations(USER_ID));
    }

    @Test
    void shouldReturnAlbumsForMe() throws IOException {
        var body = read("/response/user/get-albums.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/albums"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getAlbums());
    }

    @Test
    void shouldReturnAlbums() throws IOException {
        var body = read("/response/user/get-albums.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/albums"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getAlbums(USER_ID));
    }

    @Test
    void shouldReturnArtistChartForMe() throws IOException {
        var body = read("/response/user/get-artist-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts/artists"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getArtistChart());
    }

    @Test
    void shouldReturnArtistChart() throws IOException {
        var body = read("/response/user/get-artist-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts/artists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getArtistChart(USER_ID));
    }

    @Test
    void shouldReturnArtistRecommendationsForMe() throws IOException {
        var body = read("/response/user/get-artist-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/artists"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getArtistRecommendations());
    }

    @Test
    void shouldReturnArtistRecommendations() throws IOException {
        var body = read("/response/user/get-artist-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/artists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getArtistRecommendations(USER_ID));
    }

    @Test
    void shouldReturnArtistsForMe() throws IOException {
        var body = read("/response/user/get-artists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/artists"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getArtists());
    }

    @Test
    void shouldReturnArtists() throws IOException {
        var body = read("/response/user/get-artists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Artist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/artists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getArtists(USER_ID));
    }

    @Test
    void shouldReturnChartForMe() throws IOException {
        var body = read("/response/user/get-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getChart());
    }

    @Test
    void shouldReturnChart() throws IOException {
        var body = read("/response/user/get-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getChart(USER_ID));
    }

    @Test
    void shouldReturnFlowForMe() throws IOException {
        var body = read("/response/user/get-flow.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/flow"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getFlow());
    }

    @Test
    void shouldReturnFlow() throws IOException {
        var body = read("/response/user/get-flow.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/flow"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getFlow(USER_ID));
    }

    @Test
    void shouldReturnFollowersForMe() throws IOException {
        var body = read("/response/user/get-followers.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/followers"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getFollowers());
    }

    @Test
    void shouldReturnFollowers() throws IOException {
        var body = read("/response/user/get-followers.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/followers"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getFollowers(USER_ID));
    }

    @Test
    void shouldReturnFollowingsForMe() throws IOException {
        var body = read("/response/user/get-followings.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/followings"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getFollowings());
    }

    @Test
    void shouldReturnFollowings() throws IOException {
        var body = read("/response/user/get-followings.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<User>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/followings"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getFollowings(USER_ID));
    }

    @Test
    void shouldReturnHistoryForMe() throws IOException {
        var body = read("/response/user/get-history.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/history"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getHistory());
    }

    @Test
    void shouldReturnHistory() throws IOException {
        var body = read("/response/user/get-history.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/history"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getHistory(USER_ID));
    }

    @Test
    void shouldReturnOptionsForMe() throws IOException {
        var body = read("/response/user/get-options.json");
        var expected = MAPPER.readValue(body, Options.class);

        stubFor(get(urlPathTemplate("/user/{userId}/options"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getOptions());
    }

    @Test
    void shouldReturnOptions() throws IOException {
        var body = read("/response/user/get-options.json");
        var expected = MAPPER.readValue(body, Options.class);

        stubFor(get(urlPathTemplate("/user/{userId}/options"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getOptions(USER_ID));
    }

    @Test
    void shouldReturnPermissionsForMe() throws IOException {
        var body = read("/response/user/get-permissions.json");
        var expected = MAPPER.readValue(body, PermissionsResponse.class);

        stubFor(get(urlPathTemplate("/user/{userId}/permissions"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPermissions());
    }

    @Test
    void shouldReturnPermissions() throws IOException {
        var body = read("/response/user/get-permissions.json");
        var expected = MAPPER.readValue(body, PermissionsResponse.class);

        stubFor(get(urlPathTemplate("/user/{userId}/permissions"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPermissions(USER_ID));
    }

    @Test
    void shouldReturnPersonalSongsForMe() throws IOException {
        var body = read("/response/user/get-personal-songs.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/personal_songs"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPersonalSongs());
    }

    @Test
    void shouldReturnPersonalSongs() throws IOException {
        var body = read("/response/user/get-personal-songs.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/personal_songs"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPersonalSongs(USER_ID));
    }

    @Test
    void shouldReturnPlaylistChartForMe() throws IOException {
        var body = read("/response/user/get-playlist-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts/playlists"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPlaylistChart());
    }

    @Test
    void shouldReturnPlaylistChart() throws IOException {
        var body = read("/response/user/get-playlist-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts/playlists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPlaylistChart(USER_ID));
    }

    @Test
    void shouldReturnPlaylistRecommendationsForMe() throws IOException {
        var body = read("/response/user/get-playlists-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/playlists"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPlaylistRecommendations());
    }

    @Test
    void shouldReturnPlaylistRecommendations() throws IOException {
        var body = read("/response/user/get-playlists-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/playlists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPlaylistRecommendations(USER_ID));
    }

    @Test
    void shouldReturnPlaylistsForMe() throws IOException {
        var body = read("/response/user/get-playlists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/playlists"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPlaylists());
    }

    @Test
    void shouldReturnPlaylists() throws IOException {
        var body = read("/response/user/get-playlists.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Playlist>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/playlists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getPlaylists(USER_ID));
    }

    @Test
    void shouldReturnRadioRecommendationsForMe() throws IOException {
        var body = read("/response/user/get-radios-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/radios"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getRadioRecommendations());
    }

    @Test
    void shouldReturnRadioRecommendations() throws IOException {
        var body = read("/response/user/get-radios-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/radios"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getRadioRecommendations(USER_ID));
    }

    @Test
    void shouldReturnRadiosForMe() throws IOException {
        var body = read("/response/user/get-radios.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/radios"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getRadios());
    }

    @Test
    void shouldReturnRadios() throws IOException {
        var body = read("/response/user/get-radios.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Radio>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/radios"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getRadios(USER_ID));
    }

    @Test
    void shouldReturnReleaseRecommendationsForMe() throws IOException {
        var body = read("/response/user/get-release-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/releases"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getReleaseRecommendations());
    }

    @Test
    void shouldReturnReleaseRecommendations() throws IOException {
        var body = read("/response/user/get-release-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Album>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/releases"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getReleaseRecommendations(USER_ID));
    }

    @Test
    void shouldReturnTrackChartForMe() throws IOException {
        var body = read("/response/user/get-track-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts/tracks"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getTrackChart());
    }

    @Test
    void shouldReturnTrackChart() throws IOException {
        var body = read("/response/user/get-track-chart.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/charts/tracks"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getTrackChart(USER_ID));
    }

    @Test
    void shouldReturnTrackRecommendationsForMe() throws IOException {
        var body = read("/response/user/get-tracks-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/tracks"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getTrackRecommendations());
    }

    @Test
    void shouldReturnTrackRecommendations() throws IOException {
        var body = read("/response/user/get-tracks-recommendations.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/recommendations/tracks"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getTrackRecommendations(USER_ID));
    }

    @Test
    void shouldReturnTracksForMe() throws IOException {
        var body = read("/response/user/get-tracks.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/tracks"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getTracks());
    }

    @Test
    void shouldReturnTracks() throws IOException {
        var body = read("/response/user/get-tracks.json");
        var expected = MAPPER.readValue(body, new TypeReference<Page<Track>>() {});

        stubFor(get(urlPathTemplate("/user/{userId}/tracks"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getTracks(USER_ID));
    }

    @Test
    void shouldReturnUserForMe() throws IOException {
        var body = read("/response/user/get-user.json");
        var expected = MAPPER.readValue(body, User.class);

        stubFor(get(urlPathTemplate("/user/{userId}"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getUser());
    }

    @Test
    void shouldReturnUser() throws IOException {
        var body = read("/response/user/get-user.json");
        var expected = MAPPER.readValue(body, User.class);

        stubFor(get(urlPathTemplate("/user/{userId}"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().getUser(USER_ID));
    }

    @Test
    void shouldRemoveAlbumForMe() throws IOException {
        var albumId = 1L;
        var body = read("/response/user/remove-album.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/albums"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("album_id", equalTo(albumId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removeAlbum(albumId));
    }

    @Test
    void shouldRemoveAlbum() throws IOException {
        var albumId = 1L;
        var body = read("/response/user/remove-album.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/albums"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("album_id", equalTo(albumId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removeAlbum(USER_ID, albumId));
    }

    @Test
    void shouldRemoveArtistForMe() throws IOException {
        var artistId = 1L;
        var body = read("/response/user/remove-artist.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/artists"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("artist_id", equalTo(artistId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removeArtist(artistId));
    }

    @Test
    void shouldRemoveArtist() throws IOException {
        var artistId = 1L;
        var body = read("/response/user/remove-artist.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/artists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("artist_id", equalTo(artistId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removeArtist(USER_ID, artistId));
    }

    @Test
    void shouldRemovePlaylistForMe() throws IOException {
        var playlistId = 1L;
        var body = read("/response/user/remove-playlist.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/playlists"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("playlist_id", equalTo(playlistId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removePlaylist(playlistId));
    }

    @Test
    void shouldRemovePlaylist() throws IOException {
        var playlistId = 1L;
        var body = read("/response/user/remove-playlist.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/playlists"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("playlist_id", equalTo(playlistId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removePlaylist(USER_ID, playlistId));
    }

    @Test
    void shouldRemovePodcastForMe() throws IOException {
        var podcastId = 1L;
        var body = read("/response/user/remove-podcast.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/podcasts"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("podcast_id", equalTo(podcastId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removePodcast(podcastId));
    }

    @Test
    void shouldRemovePodcast() throws IOException {
        var podcastId = 1L;
        var body = read("/response/user/remove-podcast.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/podcasts"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("podcast_id", equalTo(podcastId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removePodcast(USER_ID, podcastId));
    }

    @Test
    void shouldRemoveRadioForMe() throws IOException {
        var radioId = 1L;
        var body = read("/response/user/remove-radio.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/radios"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("radio_id", equalTo(radioId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removeRadio(radioId));
    }

    @Test
    void shouldRemoveRadio() throws IOException {
        var radioId = 1L;
        var body = read("/response/user/remove-radio.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/radios"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("radio_id", equalTo(radioId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removeRadio(USER_ID, radioId));
    }

    @Test
    void shouldRemoveTrackForMe() throws IOException {
        var trackId = 1L;
        var body = read("/response/user/remove-track.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/tracks"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("track_id", equalTo(trackId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removeTrack(trackId));
    }

    @Test
    void shouldRemoveTrack() throws IOException {
        var trackId = 1L;
        var body = read("/response/user/remove-track.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/tracks"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("track_id", equalTo(trackId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().removeTrack(USER_ID, trackId));
    }

    @Test
    void shouldUnfollowUserForMe() throws IOException {
        var followeeId = 1L;
        var body = read("/response/user/unfollow-user.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/followings"))
                .withPathParam("userId", equalTo("me"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("user_id", equalTo(followeeId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().unfollowUser(followeeId));
    }

    @Test
    void shouldUnfollowUser() throws IOException {
        var followeeId = 1L;
        var body = read("/response/user/unfollow-user.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubFor(delete(urlPathTemplate("/user/{userId}/followings"))
                .withPathParam("userId", equalTo(USER_ID))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .withQueryParam("user_id", equalTo(followeeId))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.user().unfollowUser(USER_ID, followeeId));
    }
}
