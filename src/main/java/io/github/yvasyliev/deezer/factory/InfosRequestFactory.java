package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.service.InfosService;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to infos.
 */
@RequiredArgsConstructor
public class InfosRequestFactory {
    private final InfosService infosService;
    private final TokenManager<AccessToken> accessTokenManager;

    /**
     * Gets the information about the API in the current country.
     *
     * @return a request to get the information about the API in the current country
     */
    public DeezerRequest<Infos> getInfos() {
        return new SimpleDeezerRequest<>(accessTokenManager, infosService::getInfos);
    }
}
