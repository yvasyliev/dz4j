package io.github.yvasyliev.deezer.v2.methods;

import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class AbstractObjectServiceMethod<T, S extends DeezerService> extends AbstractServiceMethod<T, S> {

    public AbstractObjectServiceMethod(S deezerService, long objectId) {
        super(deezerService);
        this.objectId = objectId;
    }
}
