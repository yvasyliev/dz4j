package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.Infos;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.SimpleDeezerRequest;
import io.github.yvasyliev.dz4j.service.InfosService;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to infos.
 */
@RequiredArgsConstructor
public class InfosRequestFactory {
    private final InfosService infosService;
    private final AuthorizationManager authorizationManager;

    /**
     * Gets the information about the API in the current country.
     *
     * @return a request to get the information about the API in the current country
     */
    public DeezerRequest<Infos> getInfos() {
        return new SimpleDeezerRequest<>(authorizationManager, infosService::getInfos);
    }
}
