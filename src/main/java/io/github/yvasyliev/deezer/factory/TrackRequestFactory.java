package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdDeezerRequest;
import io.github.yvasyliev.deezer.request.UpdateTrackDeezerRequest;
import io.github.yvasyliev.deezer.service.TrackService;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to tracks.
 */
@RequiredArgsConstructor
public class TrackRequestFactory {
    private final TrackService trackService;
    private final TokenManager<AccessToken> accessTokenManager;

    /**
     * Creates a request to get a track.
     *
     * @param trackId track ID
     * @return request to get a track
     */
    public DeezerRequest<Track> getTrack(long trackId) {
        return new GetByIdDeezerRequest<>(trackId, trackService::getTrack);
    }

    /**
     * Creates a request to update a track.
     *
     * @param trackId track ID
     * @return request to update a track
     */
    public UpdateTrackDeezerRequest updateTrack(long trackId) {
        return new UpdateTrackDeezerRequest(trackId, accessTokenManager, trackService);
    }
}
