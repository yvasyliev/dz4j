package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchAlbum extends AbstractDzAdvancedSearchMethod<Album> {
    public AdvancedSearchAlbum(SearchService searchService, Gson gson) {
        super(searchService, gson);
    }

    @Override
    public CompletableFuture<Page<Album, DzAdvancedSearchMethod<Album>>> executeAsync() {
        return searchService.advancedSearchAlbumAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_ALBUM + getQueryParams();
    }
}
