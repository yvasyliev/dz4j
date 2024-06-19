package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface GenreService extends DeezerService {
    String GENRE = "/genre/{genreId}";
    String GENRES = "/genre";
    String GENRE_ARTISTS = "/genre/{genreId}/artists";
    String GENRE_RADIOS = "/genre/{genreId}/radios";

    @RequestLine(GET + GENRE)
    CompletableFuture<Genre> getGenreAsync(@Param("genreId") long genreId);

    @RequestLine(GET + GENRES)
    CompletableFuture<Page<Genre, DzPagingMethod<Genre>>> getGenresAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + GENRE_ARTISTS)
    CompletableFuture<Page<Artist, DzPagingMethod<Artist>>> getGenreArtistsAsync(@Param("genreId") long genreId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + GENRE_RADIOS)
    CompletableFuture<Page<Radio, DzPagingMethod<Radio>>> getGenreRadiosAsync(@Param("genreId") long genreId, @QueryMap Map<String, Object> queryParams);
}
