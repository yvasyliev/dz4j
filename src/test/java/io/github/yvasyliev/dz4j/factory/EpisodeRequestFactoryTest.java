package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.BookmarkResponse;
import io.github.yvasyliev.dz4j.model.Episode;
import io.github.yvasyliev.dz4j.service.EpisodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EpisodeRequestFactoryTest {
    @InjectMocks private EpisodeRequestFactory episodeRequestFactory;
    @Mock private EpisodeService episodeService;
    @Mock private AuthorizationManager authorizationManager;

    @Test
    void testBookmarkEpisode() {
        var episodeId = 123L;
        var offset = 30;
        var accessToken = new AccessToken("test-token");
        var expected = new BookmarkResponse(true);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(episodeService.bookmarkEpisode(episodeId, accessToken, offset))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = episodeRequestFactory.bookmarkEpisode(episodeId, offset).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetEpisode() {
        var episodeId = 123L;
        var accessToken = new AccessToken("test-token");
        var expected = Episode.builder().id(456L).build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(episodeService.getEpisode(episodeId, accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = episodeRequestFactory.getEpisode(episodeId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testUnbookmarkEpisode() {
        var episodeId = 123L;
        var accessToken = new AccessToken("test-accessToken");
        var expected = new BookmarkResponse(false);

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(episodeService.unbookmarkEpisode(episodeId, accessToken))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = episodeRequestFactory.unbookmarkEpisode(episodeId).execute();

        assertEquals(expected, actual);
    }
}
