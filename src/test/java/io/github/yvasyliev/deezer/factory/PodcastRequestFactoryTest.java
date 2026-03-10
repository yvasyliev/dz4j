package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Episode;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Podcast;
import io.github.yvasyliev.deezer.service.PodcastService;
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
    @Mock private TokenManager<AccessToken> accessTokenManager;

    @Test
    void testGetEpisodes() {
        var podcastId = 123L;
        var token = "test-token";
        var expected = Page.<Episode>builder()
                .data(Episode.builder().id(456L).build())
                .build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(podcastService.getEpisodes(podcastId, token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = podcastRequestFactory.getEpisodes(podcastId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetPodcast() {
        var podcastId = 123L;
        var token = "test-token";
        var expected = Podcast.builder().id(podcastId).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(podcastService.getPodcast(podcastId, token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = podcastRequestFactory.getPodcast(podcastId).execute();

        assertEquals(expected, actual);
    }
}
