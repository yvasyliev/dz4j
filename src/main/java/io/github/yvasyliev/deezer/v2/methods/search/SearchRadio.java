package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchRadio extends SearchMethod<Radio> {
    public SearchRadio(Gson gson, SearchService searchService, String q) {
        super(gson, searchService, q);
    }

    @Override
    public Page<Radio, SearchMethod<Radio>> execute() {
        return searchService.searchRadio(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Radio, SearchMethod<Radio>>> executeAsync() {
        return searchService.searchRadioAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_RADIO + getQueryParams();
    }
}
