package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.feign.AccessTokenExpander;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;

import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface InfosService {
    /**
     * Gets the information about the API in the current country.
     *
     * @param accessToken Deezer access token
     * @return the information about the API in the current country
     */
    @RequestLine("GET /infos?access_token={accessToken}")
    CompletableFuture<Infos> getInfos(
            @Param(value = "accessToken", expander = AccessTokenExpander.class) AccessToken accessToken
    );
}
