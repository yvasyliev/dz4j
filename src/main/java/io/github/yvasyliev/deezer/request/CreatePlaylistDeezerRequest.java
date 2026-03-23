package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

/**
 * A request to create a new playlist for a user on Deezer.
 */
@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class CreatePlaylistDeezerRequest extends AbstractDeezerRequest<Playlist> {
    private final String userId;
    private final TokenManager<AccessToken> accessTokenManager;
    private final String title;
    private final UserService userService;
    private String description;

    @Override
    protected CompletableFuture<Playlist> doExecuteAsync() {
        return accessTokenManager.getToken()
                .thenCompose(accessToken -> userService.createPlaylist(userId, accessToken, title, description));
    }
}
