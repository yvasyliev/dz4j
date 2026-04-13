package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Episode;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Podcast;
import io.github.yvasyliev.dz4j.service.PodcastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PodcastRequestFactoryTest {
    @InjectMocks private PodcastRequestFactory podcastRequestFactory;
    @Mock private PodcastService podcastService;
    @Mock private AuthorizationManager authorizationManager;

    @Test
    void testGetEpisodes() {
        var podcastId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Page.<Episode>builder()
                .data(Episode.builder().id(456L).build())
                .build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(podcastService.getEpisodes(podcastId, accessToken))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = podcastRequestFactory.getEpisodes(podcastId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPodcast() {
        var podcastId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Podcast.builder().id(podcastId).build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(podcastService.getPodcast(podcastId, accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = podcastRequestFactory.getPodcast(podcastId).execute();

        assertEquals(expected, actual);
    }
}
