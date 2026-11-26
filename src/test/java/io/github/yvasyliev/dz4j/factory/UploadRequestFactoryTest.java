package io.github.yvasyliev.dz4j.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import feign.form.FormData;
import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.authorization.UploadTokenManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Infos;
import io.github.yvasyliev.dz4j.service.UploadService;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UploadRequestFactoryTest {
    @InjectMocks
    private UploadRequestFactory uploadRequestFactory;

    @Mock
    private UploadService uploadService;

    @Mock
    private AuthorizationManager authorizationManager;

    @Mock
    private UploadTokenManager uploadTokenManager;

    @Test
    void testUploadPlaylistCoverWithFile() {
        var playlistId = 123L;
        var cover = new File("cover.jpg");
        var accessToken = new AccessToken("access-token");
        var infos = Infos.builder().uploadToken("upload-token").build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(uploadTokenManager.getUploadToken()).thenReturn(CompletableFuture.completedFuture(infos));
        when(uploadService.uploadPlaylistCover(playlistId, accessToken, infos, cover))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = uploadRequestFactory.uploadPlaylistCover(playlistId, cover).execute();

        assertThat(actual).isTrue();
    }

    @Test
    void testUploadPlaylistCoverWithFormData() {
        var playlistId = 123L;
        var cover = new byte[] {0x01, 0x02, 0x03};
        var fileName = "cover.jpg";
        var accessToken = new AccessToken("access-token");
        var infos = Infos.builder().uploadToken("upload-token").build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(uploadTokenManager.getUploadToken()).thenReturn(CompletableFuture.completedFuture(infos));
        when(uploadService.uploadPlaylistCover(playlistId, accessToken, infos, new FormData(null, fileName, cover)))
                .thenReturn(CompletableFuture.completedFuture(true));

        var actual = uploadRequestFactory
                .uploadPlaylistCover(playlistId, cover, fileName)
                .execute();

        assertThat(actual).isTrue();
    }
}
