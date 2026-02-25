package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.AuthorizationContext;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class CreatePlaylistDeezerRequest extends AbstractDeezerRequest<Playlist> {
    private final String userId;
    private final AuthorizationContext authorizationContext;
    private final String title;
    private final UserService userService;
    private String description;

    @Override
    protected CompletableFuture<Playlist> doExecuteAsync() {
        return userService.createPlaylist(
                userId,
                authorizationContext.getAccessTokenProvider().getAccessToken(),
                title,
                description
        );
    }
}
