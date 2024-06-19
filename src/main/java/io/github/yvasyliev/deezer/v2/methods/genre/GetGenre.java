package io.github.yvasyliev.deezer.v2.methods.genre;

import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzIdMethod;

import java.util.concurrent.CompletableFuture;

public class GetGenre extends AbstractDzIdMethod<Genre, GenreService> {
    public GetGenre(GenreService genreService, long genreId) {
        super(genreService, genreId);
    }

    @Override
    public CompletableFuture<Genre> executeAsync() {
        return deezerService.getGenreAsync(objectId);
    }

    @Override
    public String toString() {
        return "/genre/" + objectId;
    }
}
