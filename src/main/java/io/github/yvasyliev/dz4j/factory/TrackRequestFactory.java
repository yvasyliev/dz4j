package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.TokenManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.IdDeezerRequest;
import io.github.yvasyliev.dz4j.request.UpdateTrackDeezerRequest;
import io.github.yvasyliev.dz4j.service.TrackService;
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
        return new IdDeezerRequest<>(trackId, trackService::getTrack);
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
