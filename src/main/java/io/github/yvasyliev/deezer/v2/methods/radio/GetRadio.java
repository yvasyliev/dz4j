package io.github.yvasyliev.deezer.v2.methods.radio;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetRadio extends AbstractDzPagingIdMethod<Radio, RadioService> {
    public GetRadio(RadioService radioService, Gson gson, long radioId) {
        super(radioService, gson, radioId);
    }

    @Override
    public CompletableFuture<Page<Radio, DzPagingMethod<Radio>>> executeAsync() {
        return deezerService.getRadioAsync(objectId);
    }

    @Override
    public String toString() {
        return "/radio/" + objectId;
    }
}
