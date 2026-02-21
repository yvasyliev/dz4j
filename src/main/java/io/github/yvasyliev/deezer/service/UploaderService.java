package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.form.FormData;
import io.github.yvasyliev.deezer.feign.AccessTokenExpander;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface UploaderService {
    /**
     * Uploads a cover image for the specified playlist.
     *
     * @param playlistId  the ID of the playlist
     * @param accessToken the access token for authentication
     * @param cover       the cover image file to upload
     * @return {@code true} if the upload was successful
     */
    @RequestLine("POST /playlist/{playlistId}")
    @Headers("Content-Type: multipart/form-data")
    CompletableFuture<Boolean> uploadPlaylistCover(
            @Param("playlistId") long playlistId,
            @Param(value = "access_token", expander = AccessTokenExpander.class) AccessToken accessToken,
            @Param("upload_token") String uploadToken,
            @Param("file") File cover
    );

    /**
     * Uploads a cover image for the specified playlist.
     *
     * @param playlistId  the ID of the playlist
     * @param accessToken the access token for authentication
     * @param cover       the cover image file to upload
     * @return {@code true} if the upload was successful
     */
    @RequestLine("POST /playlist/{playlistId}")
    @Headers("Content-Type: multipart/form-data")
    CompletableFuture<Boolean> uploadPlaylistCover(
            @Param("playlistId") long playlistId,
            @Param(value = "access_token", expander = AccessTokenExpander.class) AccessToken accessToken,
            @Param("upload_token") String uploadToken,
            @Param("file") FormData cover
    );
}
