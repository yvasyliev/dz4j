package io.github.yvasyliev.deezer.v2.methods.genre;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetGenreArtists extends AbstractDzPagingIdMethod<Artist, GenreService> {
    public GetGenreArtists(GenreService genreService, Gson gson, long genreId) {
        super(genreService, gson, genreId);
    }

    @Override
    public CompletableFuture<Page<Artist, DzPagingMethod<Artist>>> executeAsync() {
        return deezerService.getGenreArtistsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/genre/" + objectId + "/artists" + getQueryParams();
    }
}
