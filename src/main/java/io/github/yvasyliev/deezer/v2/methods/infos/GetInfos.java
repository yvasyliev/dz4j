package io.github.yvasyliev.deezer.v2.methods.infos;

import io.github.yvasyliev.deezer.objects.Infos;
import io.github.yvasyliev.deezer.service.InfosService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzMethod;

import java.util.concurrent.CompletableFuture;

public class GetInfos extends AbstractDzMethod<Infos, InfosService> {
    public GetInfos(InfosService infosService) {
        super(infosService);
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
