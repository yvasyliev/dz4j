package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Chart;
import io.github.yvasyliev.deezer.model.Editorial;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.deezer.request.IdDeezerRequest;
import io.github.yvasyliev.deezer.request.PagingDeezerRequest;
import io.github.yvasyliev.deezer.request.SimplePagingDeezerRequest;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.util.TriFunction;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Factory for creating requests related to editorials.
 */
@RequiredArgsConstructor
public class EditorialRequestFactory {
    private final EditorialService editorialService;

    /**
     * Creates a request to retrieve a list of editorials for the current country.
     *
     * @return a request that, when executed, will return a list of editorials
     */
    public PagingDeezerRequest<Page<Editorial>> getEditorials() {
        return new SimplePagingDeezerRequest<>(editorialService::getEditorials);
    }

    /**
     * Creates a request to retrieve an editorial for a specific genre.
     *
     * @param genreId the genre ID
     * @return a request that, when executed, will return an editorial for a specific genre
     */
    public DeezerRequest<Editorial> getEditorial(long genreId) {
        return createDeezerRequest(genreId, editorialService::getEditorial);
    }

    /**
     * Creates a request to retrieve a list of albums selected every week by the Deezer Team.
     *
     * @param genreId the genre ID
     * @return a request that, when executed, will return a list of albums selected every week by the Deezer Team
     */
    public DeezerRequest<Page<Album>> getEditorialSelection(long genreId) {
        return createDeezerRequest(genreId, editorialService::getEditorialSelection);
    }

    /**
     * Creates a request to retrieve the new releases per genre for the current country.
     *
     * @param genreId the genre ID
     * @return a request that, when executed, will return the new releases per genre for the current country
     */
    public PagingDeezerRequest<Page<Album>> getEditorialReleases(long genreId) {
        return createPagingDeezerRequest(genreId, editorialService::getEditorialReleases);
    }

    /**
     * Creates a request to retrieve the top tracks, albums, artists, playlists and podcasts per genre for the current
     * country.
     *
     * @param genreId the genre ID
     * @return a request that, when executed, will return the top tracks, albums, artists, playlists and podcasts per
     *         genre for the current country
     */
    public PagingDeezerRequest<Page<Chart>> getEditorialCharts(long genreId) {
        return createPagingDeezerRequest(genreId, editorialService::getEditorialCharts);
    }

    private <T> DeezerRequest<T> createDeezerRequest(long genreId, Function<Long, CompletableFuture<T>> asyncMethod) {
        return new IdDeezerRequest<>(genreId, asyncMethod);
    }

    private <T> PagingDeezerRequest<Page<T>> createPagingDeezerRequest(
            long genreId,
            TriFunction<Long, Integer, Integer, CompletableFuture<Page<T>>> asyncMethod
    ) {
        return new GetByIdPagingDeezerRequest<>(genreId, asyncMethod);
    }
}
