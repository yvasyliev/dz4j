package io.github.yvasyliev.deezer.v2.methods.genre;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetGenreRadios extends AbstractDzPagingIdMethod<Radio, GenreService> {
    public GetGenreRadios(GenreService genreService, Gson gson, long genreId) {
        super(genreService, gson, genreId);
    }

    @Override
    public CompletableFuture<Page<Radio, DzPagingMethod<Radio>>> executeAsync() {
        return deezerService.getGenreRadiosAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/genre/" + objectId + "/radios" + getQueryParams();
    }
}
