package io.github.yvasyliev.deezer.request.playlist;

import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.request.AbstractDeezerRequest;
import io.github.yvasyliev.deezer.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class UpdatePlaylistRequest extends AbstractDeezerRequest<Boolean> {
    private final PlaylistService playlistService;
    private final long playlistId;
    private final AccessToken accessToken;
    private String title;
    private String description;
    private Boolean isPublic;
    private Boolean collaborative;

    @Override
    protected CompletableFuture<Boolean> doExecuteAsync() {
        return playlistService.updatePlaylist(playlistId, accessToken, title, description, isPublic, collaborative);
    }
}
