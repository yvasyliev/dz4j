package io.github.yvasyliev.deezer.v2.methods.genre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetGenre extends AbstractObjectServiceMethod<Genre, GenreService> {
    @Expose
    @SerializedName(OBJECT_ID)
    protected final long objectId;

    public GetGenre(GenreService genreService, long genreId) {
        super(genreService, genreId);
    }

    @Override
    public Genre execute() {
        return deezerService.getGenre(objectId);
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
