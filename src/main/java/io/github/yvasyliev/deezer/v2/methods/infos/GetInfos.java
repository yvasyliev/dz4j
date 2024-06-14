package io.github.yvasyliev.deezer.v2.methods.infos;

import io.github.yvasyliev.deezer.objects.Infos;
import io.github.yvasyliev.deezer.service.InfosService;
import io.github.yvasyliev.deezer.v2.methods.AbstractServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetInfos extends AbstractServiceMethod<Infos, InfosService> {
    public GetInfos(InfosService infosService) {
        super(infosService);
    }

    @Override
    public Infos execute() {
        return deezerService.getInfos();
    }

    @Override
    public CompletableFuture<Infos> executeAsync() {
        return deezerService.getInfosAsync();
    }

    @Override
    public String toString() {
        return InfosService.INFOS;
    }
}
