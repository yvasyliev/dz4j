package io.github.yvasyliev.deezer.service;

import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Infos;

import java.util.concurrent.CompletableFuture;

public interface InfosService extends DeezerService {
    String INFOS = "/infos";

    @RequestLine(GET + INFOS)
    CompletableFuture<Infos> getInfosAsync();
}
