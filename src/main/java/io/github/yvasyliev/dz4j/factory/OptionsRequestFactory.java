package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Options;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.SimpleDeezerRequest;
import io.github.yvasyliev.dz4j.service.OptionsService;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to options.
 */
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
