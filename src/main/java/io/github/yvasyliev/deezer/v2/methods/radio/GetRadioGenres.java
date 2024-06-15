package io.github.yvasyliev.deezer.v2.methods.radio;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetRadioGenres extends AbstractServicePagingMethod<Genre, RadioService> {
    protected final RadioService deezerService;
    @Expose
    @SerializedName(INDEX)
    private Integer index;
    @Expose
    @SerializedName(LIMIT)
    private Integer limit;

    public GetRadioGenres(Gson gson, RadioService radioService) {
        super(gson, radioService);
    }

    @Override
    public Page<Genre, PagingMethod<Genre>> execute() {
        return deezerService.getRadioGenres(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Genre, PagingMethod<Genre>>> executeAsync() {
        return deezerService.getRadioGenresAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return RadioService.RADIO_GENRES + getQueryParams();
    }
}
