package io.github.yvasyliev.deezer.v2.methods.genre;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetGenreArtists extends AbstractObjectServicePagingMethod<Artist, GenreService> {
    public GetGenreArtists(Gson gson, GenreService genreService, long genreId) {
        super(gson, genreService, genreId);
    }

    @Override
    public Page<Artist, PagingMethod<Artist>> execute() {
        return deezerService.getGenreArtists(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Artist, PagingMethod<Artist>>> executeAsync() {
        return deezerService.getGenreArtistsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/genre/" + objectId + "/artists" + getQueryParams();
    }
}
