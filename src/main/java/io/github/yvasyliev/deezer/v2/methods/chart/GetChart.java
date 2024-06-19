package io.github.yvasyliev.deezer.v2.methods.chart;

import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzMethod;

import java.util.concurrent.CompletableFuture;

public class GetChart extends AbstractDzMethod<Chart, ChartService> {
    public GetChart(ChartService chartService) {
        super(chartService);
    }

    @Override
    public CompletableFuture<Chart> executeAsync() {
        return deezerService.getChartAsync();
    }

    @Override
    public String toString() {
        return ChartService.CHART;
    }
}
