package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchUser extends AdvancedSearchMethod<User> {
    public AdvancedSearchUser(Gson gson, SearchService searchService) {
        super(gson, searchService);
    }

    @Override
    public Page<User, AdvancedSearchMethod<User>> execute() {
        return searchService.advancedSearchUser(getQueryParams());
    }

    @Override
    public CompletableFuture<Page<User, AdvancedSearchMethod<User>>> executeAsync() {
        return searchService.advancedSearchUserAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_USER + getQueryParams();
    }
}
