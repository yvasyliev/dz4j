package io.github.yvasyliev.deezer.v2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.ToNumberPolicy;
import feign.AsyncFeign;
import feign.Logger;
import feign.gson.GsonDecoder;
import io.github.yvasyliev.deezer.json.deserializers.DurationDeserializer;
import io.github.yvasyliev.deezer.json.deserializers.LocalDateDeserializer;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Chart;
import io.github.yvasyliev.deezer.objects.Editorial;
import io.github.yvasyliev.deezer.objects.Genre;
import io.github.yvasyliev.deezer.objects.Infos;
import io.github.yvasyliev.deezer.objects.Options;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Podcast;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.objects.SearchHistory;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
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
import io.github.yvasyliev.deezer.v2.json.creators.AbstractPagingMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.AdvancedSearchMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.PagingMethodCreator;
import io.github.yvasyliev.deezer.v2.json.creators.SearchMethodCreator;
import io.github.yvasyliev.deezer.v2.json.deserializers.AdvancedSearchMethodDeserializer;
import io.github.yvasyliev.deezer.v2.json.deserializers.MethodDeserializer;
import io.github.yvasyliev.deezer.v2.logger.DeezerLogger;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.Method;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbum;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbumFans;
import io.github.yvasyliev.deezer.v2.methods.album.GetAlbumTracks;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtist;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistAlbums;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistFans;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistPlaylists;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistRadio;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistRelated;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistTop;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChart;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartAlbums;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartArtists;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartById;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartPlaylists;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartPodcasts;
import io.github.yvasyliev.deezer.v2.methods.chart.GetChartTracks;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorial;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorialCharts;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorialReleases;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorialSelection;
import io.github.yvasyliev.deezer.v2.methods.editorial.GetEditorials;
import io.github.yvasyliev.deezer.v2.methods.genre.GetGenre;
import io.github.yvasyliev.deezer.v2.methods.genre.GetGenreArtists;
import io.github.yvasyliev.deezer.v2.methods.genre.GetGenreRadios;
import io.github.yvasyliev.deezer.v2.methods.genre.GetGenres;
import io.github.yvasyliev.deezer.v2.methods.infos.GetInfos;
import io.github.yvasyliev.deezer.v2.methods.options.GetOptions;
import io.github.yvasyliev.deezer.v2.methods.playlist.GetPlaylist;
import io.github.yvasyliev.deezer.v2.methods.playlist.GetPlaylistFans;
import io.github.yvasyliev.deezer.v2.methods.playlist.GetPlaylistRadio;
import io.github.yvasyliev.deezer.v2.methods.playlist.GetPlaylistTracks;
import io.github.yvasyliev.deezer.v2.methods.radio.GetRadio;
import io.github.yvasyliev.deezer.v2.methods.radio.GetRadioGenres;
import io.github.yvasyliev.deezer.v2.methods.radio.GetRadioLists;
import io.github.yvasyliev.deezer.v2.methods.radio.GetRadioTop;
import io.github.yvasyliev.deezer.v2.methods.radio.GetRadioTracks;
import io.github.yvasyliev.deezer.v2.methods.radio.GetRadios;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearch;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearchAlbum;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearchArtist;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearchPlaylist;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearchRadio;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearchTrack;
import io.github.yvasyliev.deezer.v2.methods.search.AdvancedSearchUser;
import io.github.yvasyliev.deezer.v2.methods.search.GetSearchHistory;
import io.github.yvasyliev.deezer.v2.methods.search.Search;
import io.github.yvasyliev.deezer.v2.methods.search.SearchAlbum;
import io.github.yvasyliev.deezer.v2.methods.search.SearchArtist;
import io.github.yvasyliev.deezer.v2.methods.search.SearchPlaylist;
import io.github.yvasyliev.deezer.v2.methods.search.SearchRadio;
import io.github.yvasyliev.deezer.v2.methods.search.SearchTrack;
import io.github.yvasyliev.deezer.v2.methods.search.SearchUser;
import io.github.yvasyliev.deezer.v2.objects.Page;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
public class DeezerClient {
    private static final String API_HOST = "https://api.deezer.com";
    private final Gson gson;
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
        return DeezerClient.custom().accessToken(accessToken).create();
    }

    @Builder(builderClassName = "DeezerClientBuilder", builderMethodName = "custom", buildMethodName = "create")
    private static DeezerClient create(
            Function<GsonBuilder, GsonBuilder> gsonBuilderCreator,
            Function<Gson, GsonDecoder> gsonDecoderCreator,
            Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCreator,
            String accessToken
    ) {
        JsonDeserializer<Method<?>> methodDeserializer = MethodDeserializer
                .builder()
                .classMap("/album/(\\d+)/fans", GetAlbumFans.class)
                .classMap("/album/(\\d+)/tracks", GetAlbumTracks.class)
                .classMap("/artist/(\\d+)/albums", GetArtistAlbums.class)
                .classMap("/artist/(\\d+)/fans", GetArtistFans.class)
                .classMap("/artist/(\\d+)/playlists", GetArtistPlaylists.class)
                .classMap("/artist/(\\d+)/radio", GetArtistRadio.class)
                .classMap("/artist/(\\d+)/related", GetArtistRelated.class)
                .classMap("/artist/(\\d+)/top", GetArtistTop.class)
                .classMap("/chart/(\\d+)/albums", GetChartAlbums.class)
                .classMap("/chart/(\\d+)/artists", GetChartArtists.class)
                .classMap("/chart/(\\d+)/playlists", GetChartPlaylists.class)
                .classMap("/chart/(\\d+)/podcasts", GetChartPodcasts.class)
                .classMap("/chart/(\\d+)/tracks", GetChartTracks.class)
                .classMap(EditorialService.EDITORIALS, GetEditorials.class)
                .classMap("/editorial/(\\d+)/releases", GetEditorialReleases.class)
                .classMap("/editorial/(\\d+)/selection", GetEditorialSelection.class)
                .classMap(GenreService.GENRES, GetGenres.class)
                .classMap("/genre/(\\d+)/artists", GetGenreArtists.class)
                .classMap("/genre/(\\d+)/radios", GetGenreRadios.class)
                .classMap("/playlist/(\\d+)/fans", GetPlaylistFans.class)
                .classMap("/playlist/(\\d+)/radio", GetPlaylistRadio.class)
                .classMap("/playlist/(\\d+)/tracks", GetPlaylistTracks.class)
                .classMap(RadioService.RADIOS, GetRadios.class)
                .classMap(RadioService.RADIO_GENRES, GetRadioGenres.class)
                .classMap(RadioService.RADIO_LISTS, GetRadioLists.class)
                .classMap(RadioService.RADIO_TOP, GetRadioTop.class)
                .classMap("/radio/(\\d+)/tracks", GetRadioTracks.class)
                .classMap(SearchService.SEARCH, Search.class)
                .classMap(SearchService.SEARCH_ALBUM, SearchAlbum.class)
                .classMap(SearchService.SEARCH_ARTIST, SearchArtist.class)
                .classMap(SearchService.SEARCH_HISTORY, GetSearchHistory.class)
                .classMap(SearchService.SEARCH_PLAYLIST, SearchPlaylist.class)
                .classMap(SearchService.SEARCH_RADIO, SearchRadio.class)
                .classMap(SearchService.SEARCH_TRACK, SearchTrack.class)
                .classMap(SearchService.SEARCH_USER, SearchUser.class)
                .build();

        JsonDeserializer<AdvancedSearchMethod<?>> advancedSearchMethodDeserializer = AdvancedSearchMethodDeserializer
                .builder()
                .classMap(SearchService.SEARCH, AdvancedSearch.class)
                .classMap(SearchService.SEARCH_ALBUM, AdvancedSearchAlbum.class)
                .classMap(SearchService.SEARCH_ARTIST, AdvancedSearchArtist.class)
                .classMap(SearchService.SEARCH_PLAYLIST, AdvancedSearchPlaylist.class)
                .classMap(SearchService.SEARCH_RADIO, AdvancedSearchRadio.class)
                .classMap(SearchService.SEARCH_TRACK, AdvancedSearchTrack.class)
                .classMap(SearchService.SEARCH_USER, AdvancedSearchUser.class)
                .build();

        AbstractPagingMethodCreator<AlbumService> albumPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<ArtistService> artistPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<ChartService> chartPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<EditorialService> editorialPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<GenreService> genrePagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<PlaylistService> playlistPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<RadioService> radioPagingMethodCreator = new PagingMethodCreator<>();
        AbstractPagingMethodCreator<SearchService> advancedSearchMethodCreator = new AdvancedSearchMethodCreator();
        AbstractPagingMethodCreator<SearchService> searchMethodCreator = new SearchMethodCreator();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                // deserializers
                .registerTypeAdapter(Duration.class, new DurationDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(PagingMethod.class, methodDeserializer)
                .registerTypeAdapter(SearchMethod.class, methodDeserializer)
                .registerTypeAdapter(AdvancedSearchMethod.class, advancedSearchMethodDeserializer)
                // creators
                .registerTypeAdapter(GetAlbumFans.class, albumPagingMethodCreator)
                .registerTypeAdapter(GetAlbumTracks.class, albumPagingMethodCreator)
                .registerTypeAdapter(GetArtistAlbums.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistFans.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistPlaylists.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistRadio.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistRelated.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetArtistTop.class, artistPagingMethodCreator)
                .registerTypeAdapter(GetChartAlbums.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetChartArtists.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetChartPlaylists.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetChartPodcasts.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetChartTracks.class, chartPagingMethodCreator)
                .registerTypeAdapter(GetEditorials.class, editorialPagingMethodCreator)
                .registerTypeAdapter(GetEditorialReleases.class, editorialPagingMethodCreator)
                .registerTypeAdapter(GetEditorialSelection.class, editorialPagingMethodCreator)
                .registerTypeAdapter(GetGenres.class, genrePagingMethodCreator)
                .registerTypeAdapter(GetGenreArtists.class, genrePagingMethodCreator)
                .registerTypeAdapter(GetGenreRadios.class, genrePagingMethodCreator)
                .registerTypeAdapter(GetPlaylistFans.class, playlistPagingMethodCreator)
                .registerTypeAdapter(GetPlaylistRadio.class, playlistPagingMethodCreator)
                .registerTypeAdapter(GetPlaylistTracks.class, playlistPagingMethodCreator)
                .registerTypeAdapter(GetRadios.class, radioPagingMethodCreator)
                .registerTypeAdapter(GetRadioGenres.class, radioPagingMethodCreator)
                .registerTypeAdapter(GetRadioLists.class, radioPagingMethodCreator)
                .registerTypeAdapter(GetRadioTop.class, radioPagingMethodCreator)
                .registerTypeAdapter(GetRadioTracks.class, radioPagingMethodCreator)
                .registerTypeAdapter(AdvancedSearch.class, advancedSearchMethodCreator)
                .registerTypeAdapter(AdvancedSearchAlbum.class, advancedSearchMethodCreator)
                .registerTypeAdapter(AdvancedSearchArtist.class, advancedSearchMethodCreator)
                .registerTypeAdapter(AdvancedSearchPlaylist.class, advancedSearchMethodCreator)
                .registerTypeAdapter(AdvancedSearchRadio.class, advancedSearchMethodCreator)
                .registerTypeAdapter(AdvancedSearchTrack.class, advancedSearchMethodCreator)
                .registerTypeAdapter(AdvancedSearchUser.class, advancedSearchMethodCreator)
                .registerTypeAdapter(Search.class, searchMethodCreator)
                .registerTypeAdapter(SearchAlbum.class, searchMethodCreator)
                .registerTypeAdapter(SearchArtist.class, searchMethodCreator)
                .registerTypeAdapter(SearchHistory.class, searchMethodCreator)
                .registerTypeAdapter(SearchPlaylist.class, searchMethodCreator)
                .registerTypeAdapter(SearchRadio.class, searchMethodCreator)
                .registerTypeAdapter(SearchTrack.class, searchMethodCreator)
                .registerTypeAdapter(SearchUser.class, searchMethodCreator);

        Gson gson = gsonBuilderCreator.apply(gsonBuilder).create();

        GsonDecoder gsonDecoder = gsonDecoderCreator.apply(gson);

        AsyncFeign.AsyncBuilder<Object> asyncFeignBuilder = AsyncFeign
                .builder()
                .logger(new DeezerLogger()) // TODO: remove
                .logLevel(Logger.Level.FULL) // TODO: remove
                .decoder(gsonDecoder);

        asyncFeignBuilder = asyncFeignBuilderCreator.apply(asyncFeignBuilder);

        AlbumService albumService = asyncFeignBuilder.target(AlbumService.class, API_HOST);
        ArtistService artistService = asyncFeignBuilder.target(ArtistService.class, API_HOST);
        ChartService chartService = asyncFeignBuilder.target(ChartService.class, API_HOST);
        EditorialService editorialService = asyncFeignBuilder.target(EditorialService.class, API_HOST);
        GenreService genreService = asyncFeignBuilder.target(GenreService.class, API_HOST);
        InfosService infosService = asyncFeignBuilder.target(InfosService.class, API_HOST);
        OptionsService optionsService = asyncFeignBuilder.target(OptionsService.class, API_HOST);
        PlaylistService playlistService = asyncFeignBuilder.target(PlaylistService.class, API_HOST);
        RadioService radioService = asyncFeignBuilder.target(RadioService.class, API_HOST);
        SearchService searchService = asyncFeignBuilder.target(SearchService.class, API_HOST);

        Stream.of(
                albumPagingMethodCreator,
                artistPagingMethodCreator,
                chartPagingMethodCreator,
                editorialPagingMethodCreator,
                genrePagingMethodCreator,
                playlistPagingMethodCreator,
                radioPagingMethodCreator,
                searchMethodCreator,
                advancedSearchMethodCreator
        ).forEach(methodCreator -> methodCreator.setGson(gson));

        albumPagingMethodCreator.setDeezerService(albumService);
        artistPagingMethodCreator.setDeezerService(artistService);
        chartPagingMethodCreator.setDeezerService(chartService);
        editorialPagingMethodCreator.setDeezerService(editorialService);
        genrePagingMethodCreator.setDeezerService(genreService);
        playlistPagingMethodCreator.setDeezerService(playlistService);
        radioPagingMethodCreator.setDeezerService(radioService);
        searchMethodCreator.setDeezerService(searchService);
        advancedSearchMethodCreator.setDeezerService(searchService);

        return new DeezerClient(
                gson,
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

    public Method<Album> getAlbum(long albumId) {
        return new GetAlbum(albumService, albumId);
    }

    public PagingMethod<User> getAlbumFans(long albumId) {
        return new GetAlbumFans(gson, albumService, albumId);
    }

    public PagingMethod<Track> getAlbumTracks(long albumId) {
        return new GetAlbumTracks(gson, albumService, albumId);
    }

    public Method<Artist> getArtist(long artistId) {
        return new GetArtist(artistService, artistId);
    }

    public PagingMethod<Album> getArtistAlbums(long artistId) {
        return new GetArtistAlbums(gson, artistService, artistId);
    }

    public PagingMethod<User> getArtistFans(long artistId) {
        return new GetArtistFans(gson, artistService, artistId);
    }

    public PagingMethod<Playlist> getArtistPlaylists(long artistId) {
        return new GetArtistPlaylists(gson, artistService, artistId);
    }

    public PagingMethod<Track> getArtistRadio(long artistId) {
        return new GetArtistRadio(gson, artistService, artistId);
    }

    public PagingMethod<Artist> getArtistRelated(long artistId) {
        return new GetArtistRelated(gson, artistService, artistId);
    }

    public PagingMethod<Track> getArtistTop(long artistId) {
        return new GetArtistRadio(gson, artistService, artistId);
    }


    public Method<Chart> getChart() {
        return new GetChart(chartService);
    }

    public Method<Chart> getChart(long chartId) {
        return new GetChartById(chartService, chartId);
    }

    public PagingMethod<Album> getChartAlbums(long chartId) {
        return new GetChartAlbums(gson, chartService, chartId);
    }

    public PagingMethod<Artist> getChartArtist(long chartId) {
        return new GetChartArtists(gson, chartService, chartId);
    }

    public PagingMethod<Playlist> getChartPlaylists(long chartId) {
        return new GetChartPlaylists(gson, chartService, chartId);
    }

    public PagingMethod<Podcast> getChartPodcasts(long chartId) {
        return new GetChartPodcasts(gson, chartService, chartId);
    }

    public PagingMethod<Track> getChartTracks(long chartId) {
        return new GetChartTracks(gson, chartService, chartId);
    }

    public Method<Editorial> getEditorial(long editorialId) {
        return new GetEditorial(editorialService, editorialId);
    }

    public PagingMethod<Editorial> getEditorials() {
        return new GetEditorials(gson, editorialService);
    }

    public Method<Chart> getEditorialCharts(long editorialId) {
        return new GetEditorialCharts(editorialService, editorialId);
    }

    public PagingMethod<Album> getEditorialReleases(long editorialId) {
        return new GetEditorialReleases(gson, editorialService, editorialId);
    }

    public PagingMethod<Album> getEditorialSelection(long editorialId) {
        return new GetEditorialSelection(gson, editorialService, editorialId);
    }

    public Method<Genre> getGenre(long genreId) {
        return new GetGenre(genreService, genreId);
    }

    public PagingMethod<Genre> getGenres() {
        return new GetGenres(gson, genreService);
    }

    public PagingMethod<Artist> getGenreArtists(long genreId) {
        return new GetGenreArtists(gson, genreService, genreId);
    }

    public PagingMethod<Radio> getGenreRadios(long genreId) {
        return new GetGenreRadios(gson, genreService, genreId);
    }

    public Method<Infos> getInfos() {
        return new GetInfos(infosService);
    }

    public Method<Options> getOptions() {
        return new GetOptions(optionsService);
    }

    public Method<Playlist> getPlaylist(long playlistId) {
        return new GetPlaylist(playlistService, playlistId);
    }

    public PagingMethod<User> getPlaylistFans(long playlistId) {
        return new GetPlaylistFans(gson, playlistService, playlistId);
    }

    public PagingMethod<Track> getPlaylistRadio(long playlistId) {
        return new GetPlaylistRadio(gson, playlistService, playlistId);
    }

    public PagingMethod<Track> getPlaylistTracks(long playlistId) {
        return new GetPlaylistTracks(gson, playlistService, playlistId);
    }

    public Method<Radio> getRadio(long radioId) {
        return new GetRadio(radioService, radioId);
    }

    public PagingMethod<Radio> getRadios() {
        return new GetRadios(gson, radioService);
    }

    public PagingMethod<Genre> getRadioGenres() {
        return new GetRadioGenres(gson, radioService);
    }

    public PagingMethod<Radio> getRadioLists() {
        return new GetRadioLists(gson, radioService);
    }

    public PagingMethod<Radio> getRadioTop() {
        return new GetRadioTop(gson, radioService);
    }

    public PagingMethod<Track> getRadioTracks(long radioId) {
        return new GetRadioTracks(gson, radioService, radioId);
    }

    public AdvancedSearchMethod<Track> search() {
        return new AdvancedSearch(gson, searchService);
    }

    public AdvancedSearchMethod<Album> searchAlbums() {
        return new AdvancedSearchAlbum(gson, searchService);
    }

    public AdvancedSearchMethod<Artist> searchArtists() {
        return new AdvancedSearchArtist(gson, searchService);
    }

    public AdvancedSearchMethod<Playlist> searchPlaylists() {
        return new AdvancedSearchPlaylist(gson, searchService);
    }

    public AdvancedSearchMethod<Radio> searchRadios() {
        return new AdvancedSearchRadio(gson, searchService);
    }

    public AdvancedSearchMethod<Track> searchTracks() {
        return new AdvancedSearchTrack(gson, searchService);
    }

    public AdvancedSearchMethod<User> searchUsers() {
        return new AdvancedSearchUser(gson, searchService);
    }

    public SearchMethod<Track> search(String q) {
        return new Search(gson, searchService, q);
    }

    public Method<Page<SearchHistory, Method<SearchHistory>>> getSearchHistory() {
        return new GetSearchHistory(gson, searchService, accessToken);
    }

    public SearchMethod<Album> searchAlbums(String q) {
        return new SearchAlbum(gson, searchService, q);
    }

    public SearchMethod<Artist> searchArtist(String q) {
        return new SearchArtist(gson, searchService, q);
    }

    public SearchMethod<Playlist> searchPlaylist(String q) {
        return new SearchPlaylist(gson, searchService, q);
    }

    public SearchMethod<Radio> searchRadio(String q) {
        return new SearchRadio(gson, searchService, q);
    }

    public SearchMethod<Track> searchTrack(String q) {
        return new SearchTrack(gson, searchService, q);
    }

    public SearchMethod<User> searchUser(String q) {
        return new SearchUser(gson, searchService, q);
    }

    public static class DeezerClientBuilder {
        private Function<GsonBuilder, GsonBuilder> gsonBuilderCreator = gsonBuilder -> gsonBuilder;
        private Function<Gson, GsonDecoder> gsonDecoderCreator = GsonDecoder::new;
        private Function<AsyncFeign.AsyncBuilder<Object>, AsyncFeign.AsyncBuilder<Object>> asyncFeignBuilderCreator = asyncFeignBuilder -> asyncFeignBuilder;
        private String accessToken;
    }
}
