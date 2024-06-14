package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface RadioService extends DeezerService{
    String RADIO = "/radio/{radioId}";
    String RADIOS = "/radio";
    String RADIO_GENRES = "/radio/genres";
    String RADIO_LISTS = "/radio/lists";
    String RADIO_TOP = "/radio/top";
    String RADIO_TRACKS = "/radio/{radioId}/tracks";

    @RequestLine(GET + RADIO)
    Radio getRadio(@Param("radioId") long radioId);

    @RequestLine(GET + RADIO)
    CompletableFuture<Radio> getRadioAsync(@Param("radioId") long radioId);

    @RequestLine(GET + RADIOS)
    Page<Radio, PagingMethod<Radio>> getRadios(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIOS)
    CompletableFuture<Page<Radio, PagingMethod<Radio>>> getRadiosAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIO_GENRES)
    Page<Genre, PagingMethod<Genre>> getRadioGenres(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIO_GENRES)
    CompletableFuture<Page<Genre, PagingMethod<Genre>>> getRadioGenresAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIO_LISTS)
    Page<Radio, PagingMethod<Radio>> getRadioLists(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIO_LISTS)
    CompletableFuture<Page<Radio, PagingMethod<Radio>>> getRadioListsAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIO_TOP)
    Page<Radio, PagingMethod<Radio>> getRadioTop(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIO_TOP)
    CompletableFuture<Page<Radio, PagingMethod<Radio>>> getRadioTopAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIO_TRACKS)
    Page<Track, PagingMethod<Track>> getRadioTracks(@Param("radioId") long radioId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + RADIO_TRACKS)
    CompletableFuture<Page<Track, PagingMethod<Track>>> getRadioTracksAsync(@Param("radioId") long radioId, @QueryMap Map<String, Object> queryParams);
}
