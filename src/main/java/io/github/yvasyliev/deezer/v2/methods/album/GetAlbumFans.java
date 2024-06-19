package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetAlbumFans extends AbstractDzPagingIdMethod<User, AlbumService> {
    public GetAlbumFans(AlbumService albumService, Gson gson, long albumId) {
        super(albumService, gson, albumId);
    }

    @Override
    public CompletableFuture<Page<User, DzPagingMethod<User>>> executeAsync() {
        return deezerService.getAlbumFansAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/album/" + objectId + "/fans" + getQueryParams();
    }
}
