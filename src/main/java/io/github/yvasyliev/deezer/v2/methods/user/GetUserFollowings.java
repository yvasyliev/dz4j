package io.github.yvasyliev.deezer.v2.methods.user;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.UserService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetUserFollowings extends AbstractDzPagingIdMethod<User, UserService> {
    public GetUserFollowings(UserService deezerService, Gson gson, long objectId) {
        super(deezerService, gson, objectId);
    }

    @Override
    public CompletableFuture<Page<User, DzPagingMethod<User>>> executeAsync() {
        return deezerService.getUserFollowingsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/user/" + objectId + "/followings" + getQueryParams();
    }
}
