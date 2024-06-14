package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class AbstractObjectServiceMethod<T, S extends DeezerService> extends AbstractServiceMethod<T, S> {
    @Expose
    @SerializedName(value = OBJECT_ID)
    protected final long objectId;

    public AbstractObjectServiceMethod(S deezerService, long objectId) {
        super(deezerService);
        this.objectId = objectId;
    }
}
