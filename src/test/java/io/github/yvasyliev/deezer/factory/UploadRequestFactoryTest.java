package io.github.yvasyliev.deezer.factory;

import feign.form.FormData;
import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import io.github.yvasyliev.deezer.service.UploadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadRequestFactoryTest {
    @Mock private UploadService uploadService;
    @Mock private TokenManager<AccessToken> accessTokenManager;
    @Mock private TokenManager<Infos> uploadTokenManager;
    private UploadRequestFactory uploadRequestFactory;

    @BeforeEach
    void setUp() {
        uploadRequestFactory = new UploadRequestFactory(uploadService, accessTokenManager, uploadTokenManager);
    }

    @Test
    void testUploadPlaylistCoverWithFile() {
        var playlistId = 123L;
        var cover = new File("cover.jpg");
        var accessToken = "access-token";
        var uploadToken = "upload-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(uploadTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(uploadToken));
        when(uploadService.uploadPlaylistCover(playlistId, accessToken, uploadToken, cover))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = uploadRequestFactory.uploadPlaylistCover(playlistId, cover).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testUploadPlaylistCoverWithFormData() {
        var playlistId = 123L;
        var cover = new byte[]{0x01, 0x02, 0x03};
        var fileName = "cover.jpg";
        var accessToken = "access-token";
        var uploadToken = "upload-token";

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(uploadTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(uploadToken));
        when(uploadService.uploadPlaylistCover(
                playlistId,
                accessToken,
                uploadToken,
                new FormData(null, fileName, cover)
        )).thenReturn(CompletableFuture.completedFuture(true));

        var actual = uploadRequestFactory.uploadPlaylistCover(playlistId, cover, fileName).execute();

        assertThat(actual).isTrue();
    }
}
