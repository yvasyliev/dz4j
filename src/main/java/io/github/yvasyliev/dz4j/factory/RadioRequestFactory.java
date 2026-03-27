package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Genre;
import io.github.yvasyliev.dz4j.model.Page;
import io.github.yvasyliev.dz4j.model.Radio;
import io.github.yvasyliev.dz4j.model.Track;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.dz4j.request.IdDeezerRequest;
import io.github.yvasyliev.dz4j.request.PagingDeezerRequest;
import io.github.yvasyliev.dz4j.request.SimpleDeezerRequest;
import io.github.yvasyliev.dz4j.request.SimplePagingDeezerRequest;
import io.github.yvasyliev.dz4j.service.RadioService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Factory for creating requests related to radios.
 */
@RequiredArgsConstructor
public class RadioRequestFactory {
    private final RadioService radioService;

    /**
     * Creates a request to get a list of radio genres.
     *
     * @return a request to get a list of radio genres
     */
    public DeezerRequest<Page<Genre>> getGenres() {
        return createDeezerRequest(radioService::getGenres);
    }

    /**
     * Creates a request to get a list of radio lists.
     *
     * @return a request to get a list of radio lists
     */
    public PagingDeezerRequest<Page<Radio>> getLists() {
        return new SimplePagingDeezerRequest<>(radioService::getLists);
    }

    /**
     * Creates a request to get a radio.
     *
     * @param radioId the radio ID
     * @return a request to get a radio
     */
    public DeezerRequest<Radio> getRadio(long radioId) {
        return new IdDeezerRequest<>(radioId, radioService::getRadio);
    }

    /**
     * Creates a request to get a list of radios.
     *
     * @return a request to get a list of radios
     */
    public DeezerRequest<Page<Radio>> getRadios() {
        return createDeezerRequest(radioService::getRadios);
    }

    /**
     * Creates a request to get a list of radio's tracks.
     *
     * @param radioId the radio ID
     * @return a request to get a list of radio's tracks
     */
    public PagingDeezerRequest<Page<Track>> getTracks(long radioId) {
        return new GetByIdPagingDeezerRequest<>(radioId, radioService::getTracks);
    }

    private <T> DeezerRequest<Page<T>> createDeezerRequest(Supplier<CompletableFuture<Page<T>>> asyncMethod) {
        return new SimpleDeezerRequest<>(asyncMethod);
    }
}
