package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchPlaylist extends SearchMethod<Playlist> {
    public SearchPlaylist(Gson gson, SearchService searchService, String q) {
        super(gson, searchService, q);
    }

    @Override
    public Page<Playlist, SearchMethod<Playlist>> execute() {
        return searchService.searchPlaylist(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Playlist, SearchMethod<Playlist>>> executeAsync() {
        return searchService.searchPlaylistAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_PLAYLIST + getQueryParams();
    }
}
