package io.github.yvasyliev.deezer.service;

import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.objects.SearchHistory;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.v2.methods.DzAdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.DzSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.Method;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface SearchService extends DeezerService {
    String SEARCH = "/search";
    String SEARCH_ALBUM = "/search/album";
    String SEARCH_ARTIST = "/search/artist";
    String SEARCH_HISTORY = "/search/history";
    String SEARCH_PLAYLIST = "/search/playlist";
    String SEARCH_RADIO = "/search/radio";
    String SEARCH_TRACK = "/search/track";
    String SEARCH_USER = "/search/user";

    @RequestLine(GET + SEARCH)
    CompletableFuture<Page<Track, DzAdvancedSearchMethod<Track>>> advancedSearchAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    CompletableFuture<Page<Album, DzAdvancedSearchMethod<Album>>> advancedSearchAlbumAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    CompletableFuture<Page<Artist, DzAdvancedSearchMethod<Artist>>> advancedSearchArtistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    CompletableFuture<Page<Playlist, DzAdvancedSearchMethod<Playlist>>> advancedSearchPlaylistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    CompletableFuture<Page<Radio, DzAdvancedSearchMethod<Radio>>> advancedSearchRadioAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    CompletableFuture<Page<Track, DzAdvancedSearchMethod<Track>>> advancedSearchTrackAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    CompletableFuture<Page<User, DzAdvancedSearchMethod<User>>> advancedSearchUserAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH)
    CompletableFuture<Page<Track, DzSearchMethod<Track>>> searchAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    CompletableFuture<Page<Album, DzSearchMethod<Album>>> searchAlbumAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    CompletableFuture<Page<Artist, DzSearchMethod<Artist>>> searchArtistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_HISTORY)
    CompletableFuture<Page<SearchHistory, Method<SearchHistory>>> getSearchHistoryAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    CompletableFuture<Page<Playlist, DzSearchMethod<Playlist>>> searchPlaylistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    CompletableFuture<Page<Radio, DzSearchMethod<Radio>>> searchRadioAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    CompletableFuture<Page<Track, DzSearchMethod<Track>>> searchTrackAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    CompletableFuture<Page<User, DzSearchMethod<User>>> searchUserAsync(@QueryMap Map<String, Object> queryParams);
}
