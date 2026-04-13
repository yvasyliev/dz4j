package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * A request to update an existing playlist on Deezer, allowing changes to the title, description, visibility, and
 * collaboration status.
 */
@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class UpdateTrackDeezerRequest extends AbstractDeezerRequest<Boolean> {
    private final long trackId;
    private final AuthorizationManager authorizationManager;
    private final TrackService trackService;
    @Nullable private String title;
    @Nullable private String artist;
    @Nullable private String album;

    @Override
    protected CompletableFuture<Boolean> doExecuteAsync() {
        return authorizationManager.getToken()
                .thenCompose(accessToken -> trackService.updateTrack(trackId, accessToken, title, artist, album));
    }
}
