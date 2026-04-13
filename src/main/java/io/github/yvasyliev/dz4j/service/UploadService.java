package io.github.yvasyliev.dz4j.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.form.FormData;
import io.github.yvasyliev.dz4j.feign.AccessTokenExpander;
import io.github.yvasyliev.dz4j.feign.UploadTokenExpander;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Infos;
import org.jspecify.annotations.Nullable;

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
     * @param infos       the upload token for authentication
     * @param cover       the cover image file to upload
     * @return {@code true} if the upload was successful
     */
    @RequestLine("POST /playlist/{playlistId}")
    @Headers("Content-Type: multipart/form-data")
    CompletableFuture<Boolean> uploadPlaylistCover(
            @Param("playlistId") long playlistId,
            @Param(value = "access_token", expander = AccessTokenExpander.class) @Nullable AccessToken accessToken,
            @Param(value = "upload_token", expander = UploadTokenExpander.class) Infos infos,
            @Param("file") File cover
    );

    /**
     * Uploads a cover image for the specified playlist.
     *
     * @param playlistId  the playlist ID
     * @param accessToken the access token for authentication
     * @param infos       the upload token for authentication
     * @param cover       the cover image file to upload
     * @return {@code true} if the upload was successful
     */
    @RequestLine("POST /playlist/{playlistId}")
    @Headers("Content-Type: multipart/form-data")
    CompletableFuture<Boolean> uploadPlaylistCover(
            @Param("playlistId") long playlistId,
            @Param(value = "access_token", expander = AccessTokenExpander.class) @Nullable AccessToken accessToken,
            @Param(value = "upload_token", expander = UploadTokenExpander.class) Infos infos,
            @Param("file") FormData cover
    );
}
