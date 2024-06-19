package io.github.yvasyliev.deezer.service;

import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Options;

import java.util.concurrent.CompletableFuture;

public interface OptionsService extends DeezerService {
    String OPTIONS = "/options";

    @RequestLine(GET + OPTIONS)
    CompletableFuture<Options> getOptionsAsync();
}
