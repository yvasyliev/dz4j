package io.github.yvasyliev.deezer.v2.methods.genre;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetGenres extends AbstractDzPagingMethod<Genre, GenreService> {
    public GetGenres(GenreService genreService, Gson gson) {
        super(genreService, gson);
    }

    @Override
    public CompletableFuture<Page<Genre, DzPagingMethod<Genre>>> executeAsync() {
        return deezerService.getGenresAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return GenreService.GENRES + getQueryParams();
    }
}
