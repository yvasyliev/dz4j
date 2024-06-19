package io.github.yvasyliev.deezer.v2.methods.chart;

import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzIdMethod;

import java.util.concurrent.CompletableFuture;

public class GetChartById extends AbstractDzIdMethod<Chart, ChartService> {
    public GetChartById(ChartService chartService, long chartId) {
        super(chartService, chartId);
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
