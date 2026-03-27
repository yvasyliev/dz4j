package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.TokenManager;
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
    @Mock private TokenManager<AccessToken> accessTokenManager;

    @Test
    void testBookmarkEpisode() {
        var episodeId = 123L;
        var offset = 30;
        var token = "test-token";
        var expected = new BookmarkResponse(true);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(episodeService.bookmarkEpisode(episodeId, token, offset))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = episodeRequestFactory.bookmarkEpisode(episodeId, offset).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetEpisode() {
        var episodeId = 123L;
        var token = "test-token";
        var expected = Episode.builder().id(456L).build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(episodeService.getEpisode(episodeId, token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = episodeRequestFactory.getEpisode(episodeId).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testUnbookmarkEpisode() {
        var episodeId = 123L;
        var token = "test-token";
        var expected = new BookmarkResponse(false);

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(episodeService.unbookmarkEpisode(episodeId, token))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = episodeRequestFactory.unbookmarkEpisode(episodeId).execute();

        assertEquals(expected, actual);
    }
}
