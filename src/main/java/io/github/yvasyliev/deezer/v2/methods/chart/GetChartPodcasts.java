package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Podcast;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartPodcasts extends AbstractObjectServicePagingMethod<Podcast, ChartService> {
    public GetChartPodcasts(Gson gson, ChartService chartService, long chartId) {
        super(gson, chartService, chartId);
    }

    @Override
    public Page<Podcast, PagingMethod<Podcast>> execute() {
        return deezerService.getChartPodcasts(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Podcast, PagingMethod<Podcast>>> executeAsync() {
        return deezerService.getChartPodcastsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/podcasts" + getQueryParams();
    }
}
