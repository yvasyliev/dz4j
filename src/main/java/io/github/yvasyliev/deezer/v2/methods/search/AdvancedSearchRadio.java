package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchRadio extends AbstractDzAdvancedSearchMethod<Radio> {
    public AdvancedSearchRadio(SearchService searchService, Gson gson) {
        super(searchService, gson);
    }

    @Override
    public CompletableFuture<Page<Radio, DzAdvancedSearchMethod<Radio>>> executeAsync() {
        return searchService.advancedSearchRadioAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_RADIO + getQueryParams();
    }
}
