package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartAlbums extends AbstractObjectServicePagingMethod<Album, ChartService> {
    public GetChartAlbums(Gson gson, ChartService chartService, long chartId) {
        super(gson, chartService, chartId);
    }

    @Override
    public Page<Album, PagingMethod<Album>> execute() {
        return deezerService.getChartAlbums(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Album, PagingMethod<Album>>> executeAsync() {
        return deezerService.getChartAlbumsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/albums" + getQueryParams();
    }
}
