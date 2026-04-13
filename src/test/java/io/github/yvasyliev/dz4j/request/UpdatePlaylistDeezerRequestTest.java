package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePlaylistDeezerRequestTest {
    @Mock private AuthorizationManager authorizationManager;
    @Mock private PlaylistService playlistService;

    @Test
    void shouldReturnResult() {
        var playlistId = 123L;
        var accessToken = new AccessToken("test-token");
        var title = "My Playlist";
        var description = "My favorite songs";
        var isPublic = true;
        var collaborative = true;

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(playlistService.updatePlaylist(playlistId, accessToken, title, description, isPublic, collaborative))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = new UpdatePlaylistDeezerRequest(playlistId, authorizationManager, playlistService)
                .title(title)
                .description(description)
                .isPublic(isPublic)
                .collaborative(collaborative)
                .execute();

        assertThat(actual).isTrue();
    }
}
