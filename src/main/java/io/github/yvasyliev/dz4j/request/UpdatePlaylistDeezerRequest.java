package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.service.PlaylistService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;

/**
 * A request to update an existing playlist on Deezer.
 */
@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class UpdatePlaylistDeezerRequest extends AbstractDeezerRequest<Boolean> {
    private final long playlistId;
    private final AuthorizationManager authorizationManager;
    private final PlaylistService playlistService;
    private @Nullable String title;
    private @Nullable String description;
    private @Nullable Boolean isPublic;
    private @Nullable Boolean collaborative;

    @Override
    protected CompletableFuture<Boolean> doExecuteAsync() {
        return authorizationManager
                .getToken()
                .thenCompose(accessToken -> playlistService.updatePlaylist(
                        playlistId, accessToken, title, description, isPublic, collaborative));
    }
}
