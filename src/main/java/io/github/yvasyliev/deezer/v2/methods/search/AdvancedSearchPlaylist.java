package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchPlaylist extends AdvancedSearchMethod<Playlist> {
    public AdvancedSearchPlaylist(Gson gson, SearchService searchService) {
        super(gson, searchService);
    }

    @Override
    public Page<Playlist, AdvancedSearchMethod<Playlist>> execute() {
        return searchService.advancedSearchPlaylist(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Playlist, AdvancedSearchMethod<Playlist>>> executeAsync() {
        return searchService.advancedSearchPlaylistAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_PLAYLIST + getQueryParams();
    }
}
