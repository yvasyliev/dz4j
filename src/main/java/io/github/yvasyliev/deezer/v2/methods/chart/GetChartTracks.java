package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartTracks extends AbstractDzPagingIdMethod<Track, ChartService> {
    public GetChartTracks(ChartService chartService, Gson gson, long chartId) {
        super(chartService, gson, chartId);
    }

    @Override
    public CompletableFuture<Page<Track, DzPagingMethod<Track>>> executeAsync() {
        return deezerService.getChartTracksAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/tracks" + getQueryParams();
    }
}
