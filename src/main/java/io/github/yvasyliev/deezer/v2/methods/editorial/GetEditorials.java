package io.github.yvasyliev.deezer.v2.methods.editorial;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetEditorials extends AbstractServicePagingMethod<Editorial, EditorialService> {
    public GetEditorials(Gson gson, EditorialService editorialService) {
        super(gson, editorialService);
    }

    @Override
    public Page<Editorial, PagingMethod<Editorial>> execute() {
        return deezerService.getEditorials(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Editorial, PagingMethod<Editorial>>> executeAsync() {
        return deezerService.getEditorialsAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return EditorialService.EDITORIALS + getQueryParams();
    }
}
