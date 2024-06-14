package io.github.yvasyliev.deezer.v2.methods.chart;

import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetChartById extends AbstractObjectServiceMethod<Chart, ChartService> {
    public GetChartById(ChartService chartService, long chartId) {
        super(chartService, chartId);
    }

    @Override
    public Chart execute() {
        return deezerService.getChart(objectId);
    }

    @Override
    public CompletableFuture<Chart> executeAsync() {
        return deezerService.getChartAsync(objectId);
    }

    @Override
    public String toString() {
        return "/chart/" + objectId;
    }
}
