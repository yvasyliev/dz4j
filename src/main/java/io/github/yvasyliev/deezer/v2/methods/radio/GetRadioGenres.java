package io.github.yvasyliev.deezer.v2.methods.radio;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetRadioGenres extends AbstractDzPagingMethod<Genre, RadioService> {
    public GetRadioGenres(RadioService radioService, Gson gson) {
        super(radioService, gson);
    }

    @Override
    public CompletableFuture<Page<Genre, DzPagingMethod<Genre>>> executeAsync() {
        return deezerService.getRadioGenresAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return RadioService.RADIO_GENRES + getQueryParams();
    }
}
