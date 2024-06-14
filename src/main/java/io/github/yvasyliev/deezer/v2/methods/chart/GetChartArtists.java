package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartArtists extends AbstractObjectServicePagingMethod<Artist, ChartService> {
    public GetChartArtists(Gson gson, ChartService chartService, long chartId) {
        super(gson, chartService, chartId);
    }

    @Override
    public Page<Artist, PagingMethod<Artist>> execute() {
        return deezerService.getChartArtists(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Artist, PagingMethod<Artist>>> executeAsync() {
        return deezerService.getChartArtistsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/artists" + getQueryParams();
    }
}
