package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface EditorialService extends DeezerService {
    String EDITORIAL = "/editorial/{editorialId}";
    String EDITORIALS = "/editorial";
    String EDITORIAL_CHARTS = "/editorial/{editorialId}/charts";
    String EDITORIAL_RELEASES = "/editorial/{editorialId}/releases";
    String EDITORIAL_SELECTION = "/editorial/{editorialId}/selection";

    @RequestLine(GET + EDITORIAL)
    Editorial getEditorial(@Param("editorialId") long editorialId);

    @RequestLine(GET + EDITORIAL)
    CompletableFuture<Editorial> getEditorialAsync(@Param("editorialId") long editorialId);

    @RequestLine(GET + EDITORIALS)
    Page<Editorial, PagingMethod<Editorial>> getEditorials(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIALS)
    CompletableFuture<Page<Editorial, PagingMethod<Editorial>>> getEditorialsAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL_CHARTS)
    Chart getEditorialCharts(@Param("editorialId") long editorialId);

    @RequestLine(GET + EDITORIAL_CHARTS)
    CompletableFuture<Chart> getEditorialChartsAsync(@Param("editorialId") long editorialId);

    @RequestLine(GET + EDITORIAL_RELEASES)
    Page<Album, PagingMethod<Album>> getEditorialReleases(@Param("editorialId") long editorialId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL_RELEASES)
    CompletableFuture<Page<Album, PagingMethod<Album>>> getEditorialReleasesAsync(@Param("editorialId") long editorialId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL_SELECTION)
    Page<Album, PagingMethod<Album>> getEditorialSelection(@Param("editorialId") long editorialId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + EDITORIAL_SELECTION)
    CompletableFuture<Page<Album, PagingMethod<Album>>> getEditorialSelectionAsync(@Param("editorialId") long editorialId, @QueryMap Map<String, Object> queryParams);
}
