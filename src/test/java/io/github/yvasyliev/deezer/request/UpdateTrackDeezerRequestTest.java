package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.service.TrackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTrackDeezerRequestTest {
    @Mock private TokenManager<AccessToken> accessTokenManager;
    @Mock private TrackService trackService;

    @Test
    void shouldReturnResult() {
        var trackId = 123L;
        var accessToken = "access_token";
        var title = "My Track";
        var artist = "My Artist";
        var album = "My Album";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(trackService.updateTrack(trackId, accessToken, title, artist, album))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = new UpdateTrackDeezerRequest(trackId, accessTokenManager, trackService)
                .title(title)
                .artist(artist)
                .album(album)
                .execute();

        assertThat(actual).isTrue();
    }
}

