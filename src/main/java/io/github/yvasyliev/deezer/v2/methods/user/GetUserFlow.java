package io.github.yvasyliev.deezer.v2.methods.user;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.UserService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetUserFlow extends AbstractDzPagingIdMethod<Track, UserService> {
    public GetUserFlow(UserService deezerService, Gson gson, long objectId) {
        super(deezerService, gson, objectId);
    }

    @Override
    public CompletableFuture<Page<Track, DzPagingMethod<Track>>> executeAsync() {
        return deezerService.getUserFlowAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/user/" + objectId + "/flow" + getQueryParams();
    }
}
