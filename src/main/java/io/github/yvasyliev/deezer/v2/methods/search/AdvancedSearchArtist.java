package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchArtist extends AdvancedSearchMethod<Artist> {
    public AdvancedSearchArtist(Gson gson, SearchService searchService) {
        super(gson, searchService);
    }

    @Override
    public Page<Artist, AdvancedSearchMethod<Artist>> execute() {
        return searchService.advancedSearchArtist(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Artist, AdvancedSearchMethod<Artist>>> executeAsync() {
        return searchService.advancedSearchArtistAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_ARTIST + getQueryParams();
    }
}
