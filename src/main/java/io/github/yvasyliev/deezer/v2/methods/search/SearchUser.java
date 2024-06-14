package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class SearchUser extends SearchMethod<User> {
    public SearchUser(Gson gson, SearchService searchService, String q) {
        super(gson, searchService, q);
    }

    @Override
    public Page<User, SearchMethod<User>> execute() {
        return searchService.searchUser(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<User, SearchMethod<User>>> executeAsync() {
        return searchService.searchUserAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_USER + getQueryParams();
    }
}
