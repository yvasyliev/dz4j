package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartPlaylists extends AbstractObjectServicePagingMethod<Playlist, ChartService> {
    public GetChartPlaylists(Gson gson, ChartService chartService, long chartId) {
        super(gson, chartService, chartId);
    }

    @Override
    public Page<Playlist, PagingMethod<Playlist>> execute() {
        return deezerService.getChartPlaylists(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Playlist, PagingMethod<Playlist>>> executeAsync() {
        return deezerService.getChartPlaylistsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/playlists" + getQueryParams();
    }
}
