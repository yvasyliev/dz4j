package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class AbstractObjectServicePagingMethod<T extends Pageable, S extends DeezerService> extends AbstractServicePagingMethod<T, S> {

    public AbstractObjectServicePagingMethod(Gson gson, S deezerService, long objectId) {
        super(gson, deezerService);
        this.objectId = objectId;
    }
}
