package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartArtists extends AbstractDzPagingIdMethod<Artist, ChartService> {
    public GetChartArtists(ChartService chartService, Gson gson, long chartId) {
        super(chartService, gson, chartId);
    }

    @Override
    public CompletableFuture<Page<Artist, DzPagingMethod<Artist>>> executeAsync() {
        return deezerService.getChartArtistsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/artists" + getQueryParams();
    }
}
