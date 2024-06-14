package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchRadio extends AdvancedSearchMethod<Radio> {
    public AdvancedSearchRadio(Gson gson, SearchService searchService) {
        super(gson, searchService);
    }

    @Override
    public Page<Radio, AdvancedSearchMethod<Radio>> execute() {
        return searchService.advancedSearchRadio(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Radio, AdvancedSearchMethod<Radio>>> executeAsync() {
        return searchService.advancedSearchRadioAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_RADIO + getQueryParams();
    }
}
