package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Genre;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Radio;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.GetByIdPagingDeezerRequest;
import io.github.yvasyliev.deezer.request.IdDeezerRequest;
import io.github.yvasyliev.deezer.request.PagingDeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.request.SimplePagingDeezerRequest;
import io.github.yvasyliev.deezer.service.RadioService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class RadioRequestService {
    private final RadioService radioService;

    public DeezerRequest<Page<Genre>> getGenres() {
        return createDeezerRequest(radioService::getGenres);
    }

    public PagingDeezerRequest<Page<Radio>> getLists() {
        return new SimplePagingDeezerRequest<>(radioService::getLists);
    }

    public DeezerRequest<Radio> getRadio(long radioId) {
        return new IdDeezerRequest<>(radioId, radioService::getRadio);
    }

    public DeezerRequest<Page<Radio>> getRadios() {
        return createDeezerRequest(radioService::getRadios);
    }

    public PagingDeezerRequest<Page<Track>> getTracks(long radioId) {
        return new GetByIdPagingDeezerRequest<>(radioId, radioService::getTracks);
    }

    private <T> DeezerRequest<Page<T>> createDeezerRequest(Supplier<CompletableFuture<Page<T>>> asyncMethod) {
        return new SimpleDeezerRequest<>(asyncMethod);
    }
}
