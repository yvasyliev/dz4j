package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchAlbum extends AdvancedSearchMethod<Album> {
    public AdvancedSearchAlbum(Gson gson, SearchService searchService) {
        super(gson, searchService);
    }

    @Override
    public Page<Album, AdvancedSearchMethod<Album>> execute() {
        return searchService.advancedSearchAlbum(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Album, AdvancedSearchMethod<Album>>> executeAsync() {
        return searchService.advancedSearchAlbumAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_ALBUM + getQueryParams();
    }
}
