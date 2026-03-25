package io.github.yvasyliev.deezer.factory;

import feign.form.FormData;
import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.service.UploadService;
import io.github.yvasyliev.deezer.util.QuadFunction;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 * Factory for creating requests related to uploading content.
 */
@RequiredArgsConstructor
public class UploadRequestFactory {
    private final UploadService uploadService;
    private final TokenManager<AccessToken> accessTokenManager;
    private final TokenManager<Infos> uploadTokenManager;

    /**
     * Creates a request to upload a cover image for the specified playlist.
     *
     * @param playlistId the playlist ID
     * @param cover      the cover image file to upload
     * @return a request that, when executed, will upload the cover image for the playlist
     */
    public DeezerRequest<Boolean> uploadPlaylistCover(long playlistId, File cover) {
        return uploadPlaylistCover(playlistId, cover, uploadService::uploadPlaylistCover);
    }

    /**
     * Creates a request to upload a cover image for the specified playlist.
     *
     * @param playlistId the playlist ID
     * @param cover      the cover image data to upload
     * @param fileName   the name of the file being uploaded
     * @return a request that, when executed, will upload the cover image for the playlist
     */
    public DeezerRequest<Boolean> uploadPlaylistCover(long playlistId, byte[] cover, String fileName) {
        return uploadPlaylistCover(playlistId, new FormData(null, fileName, cover), uploadService::uploadPlaylistCover);
    }

    private <T> DeezerRequest<Boolean> uploadPlaylistCover(
            long playlistId,
            T cover,
            QuadFunction<Long, String, String, T, CompletableFuture<Boolean>> asyncMethod
    ) {
        return new SimpleDeezerRequest<>(
                accessTokenManager,
                uploadTokenManager,
                (accessToken, uploadToken) -> asyncMethod.apply(playlistId, accessToken, uploadToken, cover)
        );
    }
}
