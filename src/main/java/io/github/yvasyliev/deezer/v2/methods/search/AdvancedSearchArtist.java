package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchArtist extends AbstractDzAdvancedSearchMethod<Artist> {
    public AdvancedSearchArtist(SearchService searchService, Gson gson) {
        super(searchService, gson);
    }

    @Override
    public CompletableFuture<Page<Artist, DzAdvancedSearchMethod<Artist>>> executeAsync() {
        return searchService.advancedSearchArtistAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_ARTIST + getQueryParams();
    }
}
