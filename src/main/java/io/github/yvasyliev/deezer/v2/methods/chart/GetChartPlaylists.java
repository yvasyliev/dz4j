package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartPlaylists extends AbstractDzPagingIdMethod<Playlist, ChartService> {
    public GetChartPlaylists(ChartService chartService, Gson gson, long chartId) {
        super(chartService, gson, chartId);
    }

    @Override
    public CompletableFuture<Page<Playlist, DzPagingMethod<Playlist>>> executeAsync() {
        return deezerService.getChartPlaylistsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/playlists" + getQueryParams();
    }
}
