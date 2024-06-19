package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDzIdMethod<T, S extends DeezerService> implements DzMethod<T> {
    protected final S deezerService; //TODO: rename to dzService

    @Expose
    @SerializedName("objectId")
    protected final long objectId;
}
