package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchArtist extends SearchMethod<Artist> {
    public SearchArtist(Gson gson, SearchService searchService, String q) {
        super(gson, searchService, q);
    }

    @Override
    public Page<Artist, SearchMethod<Artist>> execute() {
        return searchService.searchArtist(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Artist, SearchMethod<Artist>>> executeAsync() {
        return searchService.searchArtistAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_ARTIST + getQueryParams();
    }
}
