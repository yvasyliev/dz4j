package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchTrack extends SearchMethod<Track> {
    public SearchTrack(Gson gson, SearchService searchService, String q) {
        super(gson, searchService, q);
    }

    @Override
    public Page<Track, SearchMethod<Track>> execute() {
        return searchService.searchTrack(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, SearchMethod<Track>>> executeAsync() {
        return searchService.searchTrackAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_TRACK + getQueryParams();
    }
}
