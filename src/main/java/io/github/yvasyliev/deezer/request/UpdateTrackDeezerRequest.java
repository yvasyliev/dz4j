package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class UpdateTrackDeezerRequest extends AbstractDeezerRequest<Boolean> {
    private final long trackId;
    private final TokenManager<AccessToken> accessTokenManager;
    private final TrackService trackService;
    private String title;
    private String artist;
    private String album;

    @Override
    protected CompletableFuture<Boolean> doExecuteAsync() {
        return accessTokenManager.getToken()
                .thenCompose(accessToken -> trackService.updateTrack(trackId, accessToken, title, artist, album));
    }
}
