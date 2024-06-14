package io.github.yvasyliev.deezer.v2.methods;

import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractServiceMethod<T, S extends DeezerService> implements Method<T> {
    protected final S deezerService;
}
