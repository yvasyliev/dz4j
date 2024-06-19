package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchArtist extends AbstractDzSearchMethod<Artist> {
    public SearchArtist(SearchService searchService, Gson gson, String q) {
        super(searchService, gson, q);
    }

    @Override
    public CompletableFuture<Page<Artist, DzSearchMethod<Artist>>> executeAsync() {
        return searchService.searchArtistAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_ARTIST + getQueryParams();
    }
}
