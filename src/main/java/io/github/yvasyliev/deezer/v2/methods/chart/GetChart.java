package io.github.yvasyliev.deezer.v2.methods.chart;

import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.v2.methods.AbstractServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetChart extends AbstractServiceMethod<Chart, ChartService> {
    public GetChart(ChartService chartService) {
        super(chartService);
    }

    @Override
    public Chart execute() {
        return deezerService.getChart();
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
