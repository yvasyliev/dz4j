package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePlaylistDeezerRequestTest {
    @Mock private TokenManager<AccessToken> accessTokenManager;
    @Mock private UserService userService;

    @Test
    void shouldReturnPlaylist() {
        var userId = "123";
        var accessToken = "access_token";
        var title = "My Playlist";
        var description = "My favorite songs";
        var expected = Playlist.builder().id(456L).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(userService.createPlaylist(userId, accessToken, title, description))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new CreatePlaylistDeezerRequest(userId, accessTokenManager, title, userService)
                .description(description)
                .execute();

        assertEquals(expected, actual);
    }
}
