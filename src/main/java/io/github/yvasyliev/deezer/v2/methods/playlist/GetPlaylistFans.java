package io.github.yvasyliev.deezer.v2.methods.playlist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetPlaylistFans extends AbstractDzPagingIdMethod<User, PlaylistService> {
    public GetPlaylistFans(PlaylistService playlistService, Gson gson, long playlistId) {
        super(playlistService, gson, playlistId);
    }

    @Override
    public CompletableFuture<Page<User, DzPagingMethod<User>>> executeAsync() {
        return deezerService.getPlaylistFansAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/playlist/" + objectId + "/fans" + getQueryParams();
    }
}
