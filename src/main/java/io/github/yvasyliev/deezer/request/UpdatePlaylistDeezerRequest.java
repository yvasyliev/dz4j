package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

/**
 * A request to update an existing playlist on Deezer.
 */
@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class UpdatePlaylistDeezerRequest extends AbstractDeezerRequest<Boolean> {
    private final long playlistId;
    private final TokenManager<AccessToken> accessTokenManager;
    private final PlaylistService playlistService;
    private String title;
    private String description;
    private Boolean isPublic;
    private Boolean collaborative;

    @Override
    protected CompletableFuture<Boolean> doExecuteAsync() {
        return accessTokenManager.getToken().thenCompose(accessToken -> playlistService.updatePlaylist(
                playlistId,
                accessToken,
                title,
                description,
                isPublic,
                collaborative
        ));
    }
}
