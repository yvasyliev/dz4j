package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchAlbum extends AbstractDzSearchMethod<Album> {
    public SearchAlbum(SearchService searchService, Gson gson, String q) {
        super(searchService, gson, q);
    }

    @Override
    public CompletableFuture<Page<Album, DzSearchMethod<Album>>> executeAsync() {
        return searchService.searchAlbumAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_ALBUM + getQueryParams();
    }
}
