package io.github.yvasyliev.dz4j.request;

import io.github.yvasyliev.dz4j.exception.DeezerException;
import io.github.yvasyliev.dz4j.model.OEmbed;
import io.github.yvasyliev.dz4j.service.OEmbedService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OEmbedDeezerRequestTest {
    @Mock private OEmbedService oEmbedService;

    @Test
    void shouldGetOEmbed() throws MalformedURLException {
        var url = "https://example.com";
        var autoplay = true;
        var maxWidth = 900;
        var maxHeight = 700;
        var radius = true;
        var tracklist = true;
        var expected = OEmbed.builder().id(123L).build();

        when(oEmbedService.getOEmbed(new URL(url), autoplay, maxWidth, maxHeight, radius, tracklist))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = new OEmbedDeezerRequest(url, oEmbedService)
                .autoplay(autoplay)
                .maxWidth(maxWidth)
                .maxHeight(maxHeight)
                .radius(radius)
                .tracklist(tracklist)
                .execute();

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowDeezerExceptionWhenUrlIsInvalid() {
        var url = "example.com";

        assertThrows(DeezerException.class, () -> new OEmbedDeezerRequest(url, oEmbedService).execute());
    }
}
