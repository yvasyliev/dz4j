package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.form.FormData;

import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 * Service for uploading content to Deezer, such as playlist cover images.
 */
@Headers("Accept: application/json")
public interface UploadService {
    /**
     * Uploads a cover image for the specified playlist.
     *
     * @param playlistId  the playlist ID
     * @param accessToken the access token for authentication
     * @param uploadToken the upload token for authentication
     * @param cover       the cover image file to upload
     * @return {@code true} if the upload was successful
     */
    @RequestLine("POST /playlist/{playlistId}")
    @Headers("Content-Type: multipart/form-data")
    CompletableFuture<Boolean> uploadPlaylistCover(
            @Param("playlistId") long playlistId,
            @Param("access_token") String accessToken,
            @Param("upload_token") String uploadToken,
            @Param("file") File cover
    );

    /**
     * Uploads a cover image for the specified playlist.
     *
     * @param playlistId  the playlist ID
     * @param accessToken the access token for authentication
     * @param cover       the cover image file to upload
     * @return {@code true} if the upload was successful
     */
    @RequestLine("POST /playlist/{playlistId}")
    @Headers("Content-Type: multipart/form-data")
    CompletableFuture<Boolean> uploadPlaylistCover(
            @Param("playlistId") long playlistId,
            @Param("access_token") String accessToken,
            @Param("upload_token") String uploadToken,
            @Param("file") FormData cover
    );
}
