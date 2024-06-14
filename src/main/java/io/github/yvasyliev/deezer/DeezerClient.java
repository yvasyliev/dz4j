package io.github.yvasyliev.deezer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.AsyncFeign;
import feign.Logger;
import feign.gson.GsonDecoder;
import io.github.yvasyliev.deezer.json.deserializers.AdvancedSearchMethodDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.DurationDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.LocalDateDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.PagingMethodDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.SearchHistoryMethodDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.SearchMethodDeserializer;
import io.github.yvasyliev.deezer.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.methods.Method;
import io.github.yvasyliev.deezer.methods.PagingMethod;
import io.github.yvasyliev.deezer.methods.SearchHistoryMethod;
import io.github.yvasyliev.deezer.methods.SearchMethod;
import io.github.yvasyliev.deezer.objects.AdvancedSearchPage;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Infos;
import io.github.yvasyliev.deezer.objects.Options;
import io.github.yvasyliev.deezer.objects.Page;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.objects.SearchHistoryPage;
import io.github.yvasyliev.deezer.objects.SearchPage;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.service.InfosService;
import io.github.yvasyliev.deezer.service.OptionsService;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.logger.DeezerLogger;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeezerClient {
    private static final String API_HOST = "https://api.deezer.com";
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final ChartService chartService;
    private final EditorialService editorialService;
    private final GenreService genreService;
    private final InfosService infosService;
    private final OptionsService optionsService;
    private final PlaylistService playlistService;
    private final RadioService radioService;
    private final SearchService searchService;
    private String accessToken;

    public static DeezerClient create() {
        return create(null);
    }

    public static DeezerClient create(String accessToken) {
        return create(null, null, null, accessToken);
    }

    @Builder(builderMethodName = "custom", buildMethodName = "create")
    private static DeezerClient create(
            Function<GsonBuilder, GsonBuilder> gsonBuilderCustomizer,
            Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCustomizer,
            Function<Gson, GsonDecoder> gsonDecoderCreator,
            String accessToken
    ) {
        Map<Pattern, Function<Long, ? extends PagingMethod<? extends Pageable>>> pagingMethodFactories = new HashMap<>();
        Map<String, Function<String, ? extends SearchMethod<? extends Pageable>>> searchMethodFactories = new HashMap<>();
        Map<String, Supplier<? extends AdvancedSearchMethod<? extends Pageable>>> advancedSearchMethodFactories = new HashMap<>();
        SearchHistoryMethodDeserializer searchHistoryMethodDeserializer = new SearchHistoryMethodDeserializer();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Duration.class, new DurationDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(PagingMethod.class, new PagingMethodDeserializer<>(pagingMethodFactories))
                .registerTypeAdapter(SearchMethod.class, new SearchMethodDeserializer<>(searchMethodFactories))
                .registerTypeAdapter(AdvancedSearchMethod.class, new AdvancedSearchMethodDeserializer<>(advancedSearchMethodFactories))
                .registerTypeAdapter(SearchHistoryMethod.class, searchHistoryMethodDeserializer);

        if (gsonBuilderCustomizer != null) {
            gsonBuilder = gsonBuilderCustomizer.apply(gsonBuilder);
        }

        Gson gson = gsonBuilder.create();

        GsonDecoder gsonDecoder = gsonDecoderCreator != null
                ? gsonDecoderCreator.apply(gson)
                : new GsonDecoder(gson);

        AsyncFeign.AsyncBuilder<Object> asyncBuilder = AsyncFeign
                .builder()
                .logger(new DeezerLogger()) // TODO: remove
                .logLevel(Logger.Level.FULL) // TODO: remove
                .decoder(gsonDecoder);

        if (asyncFeignBuilderCustomizer != null) {
            asyncBuilder = asyncFeignBuilderCustomizer.apply(asyncBuilder);
        }

        AlbumService albumService = asyncBuilder.target(AlbumService.class, API_HOST);
        ArtistService artistService = asyncBuilder.target(ArtistService.class, API_HOST);
        ChartService chartService = asyncBuilder.target(ChartService.class, API_HOST);
        EditorialService editorialService = asyncBuilder.target(EditorialService.class, API_HOST);
        GenreService genreService = asyncBuilder.target(GenreService.class, API_HOST);
        InfosService infosService = asyncBuilder.target(InfosService.class, API_HOST);
        OptionsService optionsService = asyncBuilder.target(OptionsService.class, API_HOST);
        PlaylistService playlistService = asyncBuilder.target(PlaylistService.class, API_HOST);
        RadioService radioService = asyncBuilder.target(RadioService.class, API_HOST);
        SearchService searchService = asyncBuilder.target(SearchService.class, API_HOST);

        return new DeezerClient(
                albumService,
                artistService,
                chartService,
                editorialService,
                genreService,
                infosService,
                optionsService,
                playlistService,
                radioService,
                searchService,
                accessToken
        );
    }

    // ALBUM METHODS

    public Method<Album> getAlbum(long albumId) {
        return method(albumService::getAlbum, albumService::getAlbumAsync, albumId);
    }

    // ARTIST METHODS

    public Method<Artist> getArtist(long artistId) {
        return method(artistService::getArtist, artistService::getArtistAsync, artistId);
    }


    // CHART METHODS

    public Method<Chart> getChart() {
        return method(chartService::getChart, chartService::getChartAsync);
    }

    public Method<Chart> getChart(long chartId) {
        return method(chartService::getChart, chartService::getChartAsync, chartId);
    }

    // EDITORIAL METHODS

    public Method<Editorial> getEditorial(long editorialId) {
        return method(editorialService::getEditorial, editorialService::getEditorialAsync, editorialId);
    }

    public Method<Chart> getEditorialCharts(long editorialId) {
        return method(editorialService::getEditorialCharts, editorialService::getEditorialChartsAsync, editorialId);
    }

    // GENRE METHODS

    public Method<Genre> getGenre(long genreId) {
        return method(genreService::getGenre, genreService::getGenreAsync, genreId);
    }

    // INFOS METHODS

    public Method<Infos> getInfos() {
        return method(infosService::getInfos, infosService::getInfosAsync);
    }

    // OPTIONS METHODS

    public Method<Options> getOptions() {
        return method(optionsService::getOptions, optionsService::getOptionsAsync);
    }

    // PLAYLIST METHODS

    public Method<Playlist> getPlaylist(long playlistId) {
        return method(playlistService::getPlaylist, playlistService::getPlaylistAsync, playlistId);
    }

    // RADIO METHODS

    public Method<Radio> getRadio(long radioId) {
        return method(radioService::getRadio, radioService::getRadioAsync, radioId);
    }

    // METHOD CREATORS

    private <T> Method<T> method(Supplier<T> invoker, Supplier<CompletableFuture<T>> asyncInvoker) {
        return new Method<>(
                queryParams -> invoker.get(),
                queryParams -> asyncInvoker.get()
        );
    }

    private <T> Method<T> method(Function<Long, T> invoker, Function<Long, CompletableFuture<T>> asyncInvoker, long objectId) {
        return method(
                () -> invoker.apply(objectId),
                () -> asyncInvoker.apply(objectId)
        );
    }

    private <T extends Pageable> PagingMethod<T> pagingMethod(
            Function<Map<String, Object>, Page<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        return pagingMethod(
                (objectId, queryParams) -> invoker.apply(queryParams),
                (objectId, queryParams) -> asyncInvoker.apply(queryParams),
                null
        );
    }

    private <T extends Pageable> PagingMethod<T> pagingMethod(
            BiFunction<Long, Map<String, Object>, Page<T>> invoker,
            BiFunction<Long, Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker,
            Long objectId
    ) {
        return pagingMethodFactory(invoker, asyncInvoker).apply(objectId);
    }

    private <T extends Pageable> SearchMethod<T> searchMethod(
            Function<Map<String, Object>, SearchPage<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<SearchPage<T>>> asyncInvoker,
            String q
    ) {
        return searchMethodFactory(invoker, asyncInvoker).apply(q);
    }

    private <T extends Pageable> AdvancedSearchMethod<T> advancedSearchMethod(
            Function<Map<String, Object>, AdvancedSearchPage<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<AdvancedSearchPage<T>>> asyncInvoker
    ) {
        return advancedSearchMethodFactory(invoker, asyncInvoker).get();
    }

    private SearchHistoryMethod searchHistoryMethod(
            Function<Map<String, Object>, SearchHistoryPage> invoker,
            Function<Map<String, Object>, CompletableFuture<SearchHistoryPage>> asyncInvoker
    ) {
        return searchHistoryMethodFactory(invoker, asyncInvoker, accessToken).get();
    }

    private static <T extends Pageable> Function<Long, PagingMethod<T>> pagingMethodFactory(
            BiFunction<Long, Map<String, Object>, Page<T>> invoker,
            BiFunction<Long, Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        return objectId -> new PagingMethod<>(
                queryParams -> invoker.apply(objectId, queryParams),
                queryParams -> asyncInvoker.apply(objectId, queryParams)
        );
    }

    private static <T extends Pageable> Function<Long, PagingMethod<T>> pagingMethodFactory(
            Function<Map<String, Object>, Page<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<Page<T>>> asyncInvoker
    ) {
        return pagingMethodFactory(
                (objectId, queryParams) -> invoker.apply(queryParams),
                (objectId, queryParams) -> asyncInvoker.apply(queryParams)
        );
    }

    private static <T extends Pageable> Function<String, SearchMethod<T>> searchMethodFactory(
            Function<Map<String, Object>, SearchPage<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<SearchPage<T>>> asyncInvoker
    ) {
        return q -> new SearchMethod<>(invoker, asyncInvoker, q);
    }

    private static <T extends Pageable> Supplier<AdvancedSearchMethod<T>> advancedSearchMethodFactory(
            Function<Map<String, Object>, AdvancedSearchPage<T>> invoker,
            Function<Map<String, Object>, CompletableFuture<AdvancedSearchPage<T>>> asyncInvoker
    ) {
        return () -> new AdvancedSearchMethod<>(invoker, asyncInvoker);
    }

    private static Supplier<SearchHistoryMethod> searchHistoryMethodFactory(
            Function<Map<String, Object>, SearchHistoryPage> invoker,
            Function<Map<String, Object>, CompletableFuture<SearchHistoryPage>> asyncInvoker,
            String accessToken
    ) {
        return () -> new SearchHistoryMethod(invoker, asyncInvoker, accessToken);
    }
}
