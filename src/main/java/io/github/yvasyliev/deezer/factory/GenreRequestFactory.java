package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Genre;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Radio;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.IdDeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.service.GenreService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Factory for creating requests related to genres.
 */
@RequiredArgsConstructor
public class GenreRequestFactory {
    private final GenreService genreService;

    /**
     * Creates a request to get all artists for a genre.
     *
     * @param genreId the genre ID
     * @return a request to get all artists for a genre
     */
    public DeezerRequest<Page<Artist>> getArtists(long genreId) {
        return createDeezerRequest(genreId, genreService::getArtists);
    }

    /**
     * Creates a request to get a genre.
     *
     * @param genreId the genre ID
     * @return a request to get a genre
     */
    public DeezerRequest<Genre> getGenre(long genreId) {
        return createDeezerRequest(genreId, genreService::getGenre);
    }

    /**
     * Creates a request to get all genres.
     *
     * @return a request to get all genres
     */
    public DeezerRequest<Page<Genre>> getGenres() {
        return new SimpleDeezerRequest<>(genreService::getGenres);
    }

    /**
     * Creates a request to get all radios for a genre.
     *
     * @param genreId the genre ID
     * @return a request to get all radios for a genre
     */
    public DeezerRequest<Page<Radio>> getRadios(long genreId) {
        return createDeezerRequest(genreId, genreService::getRadios);
    }

    private <T> DeezerRequest<T> createDeezerRequest(long genreId, Function<Long, CompletableFuture<T>> asyncMethod) {
        return new IdDeezerRequest<>(genreId, asyncMethod);
    }
}
