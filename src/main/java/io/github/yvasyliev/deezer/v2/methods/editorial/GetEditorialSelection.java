package io.github.yvasyliev.deezer.v2.methods.editorial;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetEditorialSelection extends AbstractDzPagingIdMethod<Album, EditorialService> {
    public GetEditorialSelection(EditorialService editorialService, Gson gson, long editorialId) {
        super(editorialService, gson, editorialId);
    }

    @Override
    public CompletableFuture<Page<Album, DzPagingMethod<Album>>> executeAsync() {
        return deezerService.getEditorialSelectionAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/editorial/" + objectId + "/selection" + getQueryParams();
    }
}
