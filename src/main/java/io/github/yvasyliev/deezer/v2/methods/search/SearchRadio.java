package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchRadio extends AbstractDzSearchMethod<Radio> {
    public SearchRadio(SearchService searchService, Gson gson, String q) {
        super(searchService, gson, q);
    }

    @Override
    public CompletableFuture<Page<Radio, DzSearchMethod<Radio>>> executeAsync() {
        return searchService.searchRadioAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_RADIO + getQueryParams();
    }
}
