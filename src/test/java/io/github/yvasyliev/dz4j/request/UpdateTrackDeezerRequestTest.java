package io.github.yvasyliev.dz4j.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.service.TrackService;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateTrackDeezerRequestTest {
    @Mock
    private AuthorizationManager authorizationManager;

    @Mock
    private TrackService trackService;

    @Test
    void shouldReturnResult() {
        var trackId = 123L;
        var accessToken = new AccessToken("test-token");
        var title = "My Track";
        var artist = "My Artist";
        var album = "My Album";

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(trackService.updateTrack(trackId, accessToken, title, artist, album))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = new UpdateTrackDeezerRequest(trackId, authorizationManager, trackService)
                .title(title)
                .artist(artist)
                .album(album)
                .execute();

        assertThat(actual).isTrue();
    }
}
