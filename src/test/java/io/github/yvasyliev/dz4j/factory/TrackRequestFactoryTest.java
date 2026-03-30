package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.service.TrackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackRequestFactoryTest {
    @InjectMocks private TrackRequestFactory trackRequestFactory;
    @Mock private TrackService trackService;
    @Mock private AuthorizationManager authorizationManager;

    @Test
    void testGetTrack() {
        var trackId = 123L;
        var expected = Track.builder().id(trackId).build();

        when(trackService.getTrack(trackId)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = trackRequestFactory.getTrack(trackId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testUpdateTrack() {
        var trackId = 123L;
        var accessToken = new AccessToken("test-token");

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(trackService.updateTrack(trackId, accessToken, null, null, null))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = trackRequestFactory.updateTrack(trackId).execute();

        assertThat(actual).isTrue();
    }
}
