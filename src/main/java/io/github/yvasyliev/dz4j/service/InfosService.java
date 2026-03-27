package io.github.yvasyliev.dz4j.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.dz4j.model.Infos;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Service for getting the information about the API in the current country.
 */
@Headers("Accept: application/json")
public interface InfosService {
    /**
     * Gets the information about the API in the current country.
     *
     * @param accessToken Deezer access token
     * @return the information about the API in the current country
     */
    @RequestLine("GET /infos?access_token={accessToken}")
    CompletableFuture<Infos> getInfos(@Param("accessToken") @Nullable String accessToken);
}
