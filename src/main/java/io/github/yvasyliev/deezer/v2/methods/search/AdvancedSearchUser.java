package io.github.yvasyliev.deezer.v2.methods.search;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class AdvancedSearchUser extends AbstractDzAdvancedSearchMethod<User> {
    public AdvancedSearchUser(SearchService searchService, Gson gson) {
        super(searchService, gson);
    }

    @Override
    public CompletableFuture<Page<User, DzAdvancedSearchMethod<User>>> executeAsync() {
        return searchService.advancedSearchUserAsync(getQueryParams());
    }

    @Override
    public String toString() {
        return SearchService.SEARCH_USER + getQueryParams();
    }
}
