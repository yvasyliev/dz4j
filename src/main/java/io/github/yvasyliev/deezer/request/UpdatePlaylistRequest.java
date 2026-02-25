package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.AuthorizationContext;
import io.github.yvasyliev.deezer.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class UpdatePlaylistRequest extends AbstractDeezerRequest<Boolean> {
    private final long playlistId;
    private final AuthorizationContext authorizationContext;
    private final PlaylistService playlistService;
    private String title;
    private String description;
    private Boolean isPublic;
    private Boolean collaborative;

    @Override
    protected CompletableFuture<Boolean> doExecuteAsync() {
        return playlistService.updatePlaylist(
                playlistId,
                authorizationContext.getAccessTokenProvider().getAccessToken(),
                title,
                description,
                isPublic,
                collaborative
        );
    }
}
