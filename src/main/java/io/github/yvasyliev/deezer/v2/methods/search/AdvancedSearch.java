package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearch extends AdvancedSearchMethod<Track> {
    public AdvancedSearch(Gson gson, SearchService searchService) {
        super(gson, searchService);
    }

    @Override
    public Page<Track, AdvancedSearchMethod<Track>> execute() {
        return searchService.advancedSearch(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, AdvancedSearchMethod<Track>>> executeAsync() {
        return searchService.advancedSearchAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH + getQueryParams();
    }
}
