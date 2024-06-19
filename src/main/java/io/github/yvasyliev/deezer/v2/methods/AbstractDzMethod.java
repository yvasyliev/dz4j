package io.github.yvasyliev.deezer.v2.methods;

import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDzMethod<T, S extends DeezerService> implements DzMethod<T> {
    protected final S deezerService; //TODO: rename to dzService
}
