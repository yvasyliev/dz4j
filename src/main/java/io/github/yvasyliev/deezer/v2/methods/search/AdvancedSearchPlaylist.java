package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchPlaylist extends AbstractDzAdvancedSearchMethod<Playlist> {
    public AdvancedSearchPlaylist(SearchService searchService, Gson gson) {
        super(searchService, gson);
    }

    @Override
    public CompletableFuture<Page<Playlist, DzAdvancedSearchMethod<Playlist>>> executeAsync() {
        return searchService.advancedSearchPlaylistAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_PLAYLIST + getQueryParams();
    }
}
