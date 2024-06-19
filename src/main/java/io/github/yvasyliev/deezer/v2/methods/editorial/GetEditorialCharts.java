package io.github.yvasyliev.deezer.v2.methods.editorial;

import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzIdMethod;

import java.util.concurrent.CompletableFuture;

public class GetEditorialCharts extends AbstractDzIdMethod<Chart, EditorialService> {
    public GetEditorialCharts(EditorialService editorialService, long editorialId) {
        super(editorialService, editorialId);
    }

    @Override
    public CompletableFuture<Chart> executeAsync() {
        return deezerService.getEditorialChartsAsync(objectId);
    }

    @Override
    public String toString() {
        return "/editorial/" + objectId + "/charts";
    }
}
