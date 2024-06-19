package io.github.yvasyliev.deezer.v2.methods.radio;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetRadioTop extends AbstractDzPagingMethod<Radio, RadioService> {
    public GetRadioTop(RadioService radioService, Gson gson) {
        super(radioService, gson);
    }

    @Override
    public CompletableFuture<Page<Radio, DzPagingMethod<Radio>>> executeAsync() {
        return deezerService.getRadioTopAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return RadioService.RADIO_TOP + getQueryParams();
    }
}
