package io.github.yvasyliev.deezer.v2.methods.editorial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetEditorial extends AbstractObjectServiceMethod<Editorial, EditorialService> {
    @Expose
    @SerializedName(OBJECT_ID)
    protected final long objectId;

    public GetEditorial(EditorialService editorialService, long editorialId) {
        super(editorialService, editorialId);
    }

    @Override
    public Editorial execute() {
        return deezerService.getEditorial(objectId);
    }

    @Override
    public CompletableFuture<Editorial> executeAsync() {
        return deezerService.getEditorialAsync(objectId);
    }

    @Override
    public String toString() {
        return "/editorial/" + objectId;
    }
}
