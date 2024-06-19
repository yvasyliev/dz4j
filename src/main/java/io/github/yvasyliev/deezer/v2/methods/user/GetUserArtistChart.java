package io.github.yvasyliev.deezer.v2.methods.user;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.UserService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetUserArtistChart extends AbstractDzPagingIdMethod<Artist, UserService> {
    public GetUserArtistChart(UserService deezerService, Gson gson, long objectId) {
        super(deezerService, gson, objectId);
    }

    @Override
    public CompletableFuture<Page<Artist, DzPagingMethod<Artist>>> executeAsync() {
        return deezerService.getUserArtistChartAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/user/" + objectId + "/charts/artists" + getQueryParams();
    }
}
