package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchAlbum extends SearchMethod<Album> {
    public SearchAlbum(Gson gson, SearchService searchService, String q) {
        super(gson, searchService, q);
    }

    @Override
    public Page<Album, SearchMethod<Album>> execute() {
        return searchService.searchAlbum(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Album, SearchMethod<Album>>> executeAsync() {
        return searchService.searchAlbumAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_ALBUM + getQueryParams();
    }
}
