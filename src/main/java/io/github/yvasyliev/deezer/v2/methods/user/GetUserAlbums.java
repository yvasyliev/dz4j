package io.github.yvasyliev.deezer.v2.methods.user;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.UserService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetUserAlbums extends AbstractDzPagingIdMethod<Album, UserService> {
    public GetUserAlbums(UserService userService, Gson gson, long userId) {
        super(userService, gson, userId);
    }

    @Override
    public CompletableFuture<Page<Album, DzPagingMethod<Album>>> executeAsync() {
        return deezerService.getUserAlbumsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/user/" + objectId + "/albums" + getQueryParams();
    }
}
