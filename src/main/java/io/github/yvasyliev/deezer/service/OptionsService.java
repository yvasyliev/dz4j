package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.Options;

import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface OptionsService {
    /**
     * Gets the user's options.
     *
     * @return the user's options
     */
    @RequestLine("GET /options")
    CompletableFuture<Options> getOptions();
}
