package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearch extends AbstractDzAdvancedSearchMethod<Track> {
    public AdvancedSearch(SearchService searchService, Gson gson) {
        super(searchService, gson);
    }

    @Override
    public CompletableFuture<Page<Track, DzAdvancedSearchMethod<Track>>> executeAsync() {
        return searchService.advancedSearchAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH + getQueryParams();
    }
}
