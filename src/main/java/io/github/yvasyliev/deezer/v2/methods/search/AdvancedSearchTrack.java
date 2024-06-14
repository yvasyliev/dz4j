package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchTrack extends AdvancedSearchMethod<Track> {
    public AdvancedSearchTrack(Gson gson, SearchService searchService) {
        super(gson, searchService);
    }

    @Override
    public Page<Track, AdvancedSearchMethod<Track>> execute() {
        return searchService.advancedSearchTrack(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, AdvancedSearchMethod<Track>>> executeAsync() {
        return searchService.advancedSearchTrackAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_TRACK + getQueryParams();
    }
}
