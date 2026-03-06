package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Options;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.service.OptionsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OptionsRequestFactory {
    private final OptionsService optionsService;

    /**
     * Creates a request to get the user's options.
     *
     * @return the user's options
     */
    public DeezerRequest<Options> getOptions() {
        return new SimpleDeezerRequest<>(optionsService::getOptions);
    }
}
