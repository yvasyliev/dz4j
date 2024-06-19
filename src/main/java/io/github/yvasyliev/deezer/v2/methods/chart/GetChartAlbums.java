package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartAlbums extends AbstractDzPagingIdMethod<Album, ChartService> {
    public GetChartAlbums(ChartService chartService, Gson gson, long chartId) {
        super(chartService, gson, chartId);
    }

    @Override
    public CompletableFuture<Page<Album, DzPagingMethod<Album>>> executeAsync() {
        return deezerService.getChartAlbumsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/albums" + getQueryParams();
    }
}
