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
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import io.github.yvasyliev.deezer.v2.methods.Method;
import io.github.yvasyliev.deezer.v2.methods.SearchMethod;
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
    Page<Track, AdvancedSearchMethod<Track>> advancedSearch(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH)
    CompletableFuture<Page<Track, AdvancedSearchMethod<Track>>> advancedSearchAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    Page<Album, AdvancedSearchMethod<Album>> advancedSearchAlbum(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    CompletableFuture<Page<Album, AdvancedSearchMethod<Album>>> advancedSearchAlbumAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    Page<Artist, AdvancedSearchMethod<Artist>> advancedSearchArtist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    CompletableFuture<Page<Artist, AdvancedSearchMethod<Artist>>> advancedSearchArtistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    Page<Playlist, AdvancedSearchMethod<Playlist>> advancedSearchPlaylist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    CompletableFuture<Page<Playlist, AdvancedSearchMethod<Playlist>>> advancedSearchPlaylistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    Page<Radio, AdvancedSearchMethod<Radio>> advancedSearchRadio(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    CompletableFuture<Page<Radio, AdvancedSearchMethod<Radio>>> advancedSearchRadioAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    Page<Track, AdvancedSearchMethod<Track>> advancedSearchTrack(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    CompletableFuture<Page<Track, AdvancedSearchMethod<Track>>> advancedSearchTrackAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    Page<User, AdvancedSearchMethod<User>> advancedSearchUser(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    CompletableFuture<Page<User, AdvancedSearchMethod<User>>> advancedSearchUserAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH)
    Page<Track, SearchMethod<Track>> search(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH)
    CompletableFuture<Page<Track, SearchMethod<Track>>> searchAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    Page<Album, SearchMethod<Album>> searchAlbum(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ALBUM)
    CompletableFuture<Page<Album, SearchMethod<Album>>> searchAlbumAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    Page<Artist, SearchMethod<Artist>> searchArtist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_ARTIST)
    CompletableFuture<Page<Artist, SearchMethod<Artist>>> searchArtistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_HISTORY)
    Page<SearchHistory, Method<SearchHistory>> getSearchHistory(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_HISTORY)
    CompletableFuture<Page<SearchHistory, Method<SearchHistory>>> getSearchHistoryAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    Page<Playlist, SearchMethod<Playlist>> searchPlaylist(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_PLAYLIST)
    CompletableFuture<Page<Playlist, SearchMethod<Playlist>>> searchPlaylistAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    Page<Radio, SearchMethod<Radio>> searchRadio(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_RADIO)
    CompletableFuture<Page<Radio, SearchMethod<Radio>>> searchRadioAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    Page<Track, SearchMethod<Track>> searchTrack(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_TRACK)
    CompletableFuture<Page<Track, SearchMethod<Track>>> searchTrackAsync(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    Page<User, SearchMethod<User>> searchUser(@QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + SEARCH_USER)
    CompletableFuture<Page<User, SearchMethod<User>>> searchUserAsync(@QueryMap Map<String, Object> queryParams);
}
