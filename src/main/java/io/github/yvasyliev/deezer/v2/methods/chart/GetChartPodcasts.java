package io.github.yvasyliev.deezer.v2.methods.chart;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Podcast;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetChartPodcasts extends AbstractDzPagingIdMethod<Podcast, ChartService> {
    public GetChartPodcasts(ChartService chartService, Gson gson, long chartId) {
        super(chartService, gson, chartId);
    }

    @Override
    public CompletableFuture<Page<Podcast, DzPagingMethod<Podcast>>> executeAsync() {
        return deezerService.getChartPodcastsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/chart/" + objectId + "/podcasts" + getQueryParams();
    }
}
