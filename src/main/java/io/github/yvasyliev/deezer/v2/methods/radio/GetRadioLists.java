package io.github.yvasyliev.deezer.v2.methods.radio;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetRadioLists extends AbstractServicePagingMethod<Radio, RadioService> {
    public GetRadioLists(Gson gson, RadioService radioService) {
        super(gson, radioService);
    }

    @Override
    public Page<Radio, PagingMethod<Radio>> execute() {
        return deezerService.getRadioLists(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Radio, PagingMethod<Radio>>> executeAsync() {
        return deezerService.getRadioListsAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return RadioService.RADIO_LISTS + getQueryParams();
    }
}
