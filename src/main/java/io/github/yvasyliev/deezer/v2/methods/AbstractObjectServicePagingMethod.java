package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class AbstractObjectServicePagingMethod<T extends Pageable, S extends DeezerService> extends AbstractServicePagingMethod<T, S> {
    @Expose(serialize = false)
    @SerializedName(value = OBJECT_ID)
    protected final long objectId;

    public AbstractObjectServicePagingMethod(Gson gson, S deezerService, long objectId) {
        super(gson, deezerService);
        this.objectId = objectId;
    }
}
