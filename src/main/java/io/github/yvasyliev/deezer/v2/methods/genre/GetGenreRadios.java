package io.github.yvasyliev.deezer.v2.methods.genre;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetGenreRadios extends AbstractObjectServicePagingMethod<Radio, GenreService> {
    public GetGenreRadios(Gson gson, GenreService genreService, long genreId) {
        super(gson, genreService, genreId);
    }

    @Override
    public Page<Radio, PagingMethod<Radio>> execute() {
        return deezerService.getGenreRadios(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Radio, PagingMethod<Radio>>> executeAsync() {
        return deezerService.getGenreRadiosAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/genre/" + objectId + "/radios" + getQueryParams();
    }
}
