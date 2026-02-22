package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.authorization.AccessTokenProvider;
import io.github.yvasyliev.deezer.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class UpdateTrackDeezerRequest extends AbstractDeezerRequest<Boolean>{
    private final long trackId;
    private final AccessTokenProvider accessTokenProvider;
    private final TrackService trackService;
    private String title;
    private String artist;
    private String album;

    @Override
    protected CompletableFuture<Boolean> doExecuteAsync() {
        return trackService.updateTrack(trackId, accessTokenProvider.getAccessToken(), title, artist, album);
    }
}
