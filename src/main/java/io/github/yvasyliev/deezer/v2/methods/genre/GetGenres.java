package io.github.yvasyliev.deezer.v2.methods.genre;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetGenres extends AbstractServicePagingMethod<Genre, GenreService> {
    protected final GenreService deezerService;
    @Expose
    @SerializedName(INDEX)
    private Integer index;
    @Expose
    @SerializedName(LIMIT)
    private Integer limit;

    public GetGenres(Gson gson, GenreService genreService) {
        super(gson, genreService);
    }

    @Override
    public Page<Genre, PagingMethod<Genre>> execute() {
        return deezerService.getGenres(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Genre, PagingMethod<Genre>>> executeAsync() {
        return deezerService.getGenresAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return GenreService.GENRES + getQueryParams();
    }
}
