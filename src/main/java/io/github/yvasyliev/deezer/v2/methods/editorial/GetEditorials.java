package io.github.yvasyliev.deezer.v2.methods.editorial;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetEditorials extends AbstractDzPagingMethod<Editorial, EditorialService> {
    public GetEditorials(EditorialService editorialService, Gson gson) {
        super(editorialService, gson);
    }

    @Override
    public CompletableFuture<Page<Editorial, DzPagingMethod<Editorial>>> executeAsync() {
        return deezerService.getEditorialsAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return EditorialService.EDITORIALS + getQueryParams();
    }
}
