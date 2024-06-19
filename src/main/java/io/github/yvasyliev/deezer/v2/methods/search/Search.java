package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class Search extends AbstractDzSearchMethod<Track> {
    public Search(SearchService searchService, Gson gson, String q) {
        super(searchService, gson, q);
    }

    @Override
    public CompletableFuture<Page<Track, DzSearchMethod<Track>>> executeAsync() {
        return searchService.searchAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH + getQueryParams();
    }
}
