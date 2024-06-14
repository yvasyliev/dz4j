package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.SearchHistory;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractQueryMethod;
import io.github.yvasyliev.deezer.v2.methods.Method;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetSearchHistory extends AbstractQueryMethod<Page<SearchHistory, Method<SearchHistory>>> {
    private final SearchService searchService;
    @Expose
    @SerializedName("access_token")
    private final String accessToken;

    public GetSearchHistory(Gson gson, SearchService searchService, String accessToken) {
        super(gson);
        this.searchService = searchService;
        this.accessToken = accessToken;
    }


    @Override
    public Page<SearchHistory, Method<SearchHistory>> execute() {
        return searchService.getSearchHistory(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<SearchHistory, Method<SearchHistory>>> executeAsync() {
        return searchService.getSearchHistoryAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_HISTORY + getQueryParams();
    }
}
