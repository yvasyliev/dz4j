package io.github.yvasyliev.deezer.v2.methods.user;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.UserService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetUserPlaylistChart extends AbstractDzPagingIdMethod<Playlist, UserService> {
    public GetUserPlaylistChart(UserService deezerService, Gson gson, long objectId) {
        super(deezerService, gson, objectId);
    }

    @Override
    public CompletableFuture<Page<Playlist, DzPagingMethod<Playlist>>> executeAsync() {
        return deezerService.getUserPlaylistChartAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/user/" + objectId + "/charts/playlists" + getQueryParams();
    }
}
