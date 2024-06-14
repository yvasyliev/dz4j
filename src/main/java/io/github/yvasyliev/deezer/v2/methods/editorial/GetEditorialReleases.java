package io.github.yvasyliev.deezer.v2.methods.editorial;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetEditorialReleases extends AbstractObjectServicePagingMethod<Album, EditorialService> {
    public GetEditorialReleases(Gson gson, EditorialService editorialService, long editorialId) {
        super(gson, editorialService, editorialId);
    }

    @Override
    public Page<Album, PagingMethod<Album>> execute() {
        return deezerService.getEditorialReleases(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Album, PagingMethod<Album>>> executeAsync() {
        return deezerService.getEditorialReleasesAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/editorial/" + objectId + "/releases" + getQueryParams();
    }
}
