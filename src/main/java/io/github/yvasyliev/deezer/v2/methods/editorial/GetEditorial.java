package io.github.yvasyliev.deezer.v2.methods.editorial;

import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzIdMethod;

import java.util.concurrent.CompletableFuture;

public class GetEditorial extends AbstractDzIdMethod<Editorial, EditorialService> {
    public GetEditorial(EditorialService editorialService, long editorialId) {
        super(editorialService, editorialId);
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
