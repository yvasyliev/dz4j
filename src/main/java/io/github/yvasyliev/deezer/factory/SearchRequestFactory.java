package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.AdvancedQuery;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Order;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Query;
import io.github.yvasyliev.deezer.model.Radio;
import io.github.yvasyliev.deezer.model.SimpleQuery;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.request.SearchDeezerRequest;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.util.QuinaryFunction;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * Factory for creating search requests.
 */
@RequiredArgsConstructor
public class SearchRequestFactory {
    private final SearchService searchService;

    //region search

    /**
     * Creates a request to search for tracks based on a simple query string.
     *
     * @param query the search query string
     * @return a request for searching tracks
     */
    public SearchDeezerRequest<Track> search(String query) {
        return search(new SimpleQuery(query));
    }

    /**
     * Creates a request to search for tracks based on an advanced query.
     *
     * @param query the advanced search query
     * @return a request for searching tracks
     */
    public SearchDeezerRequest<Track> search(AdvancedQuery query) {
        return search((Query) query);
    }

    private SearchDeezerRequest<Track> search(Query query) {
        return createSearchDeezerRequest(query, searchService::search);
    }

    //endregion

    //region searchAlbum

    /**
     * Creates a request to search for albums based on a simple query string.
     *
     * @param query the search query string
     * @return a request for searching albums
     */
    public SearchDeezerRequest<Album> searchAlbum(String query) {
        return searchAlbum(new SimpleQuery(query));
    }

    /**
     * Creates a request to search for albums based on an advanced query.
     *
     * @param query the advanced search query
     * @return a request for searching albums
     */
    public SearchDeezerRequest<Album> searchAlbum(AdvancedQuery query) {
        return searchAlbum((Query) query);
    }

    private SearchDeezerRequest<Album> searchAlbum(Query query) {
        return createSearchDeezerRequest(query, searchService::searchAlbum);
    }

    //endregion

    //region searchArtist

    /**
     * Creates a request to search for artists based on a simple query string.
     *
     * @param query the search query string
     * @return a request for searching artists
     */
    public SearchDeezerRequest<Artist> searchArtist(String query) {
        return searchArtist(new SimpleQuery(query));
    }

    /**
     * Creates a request to search for artists based on an advanced query.
     *
     * @param query the advanced search query
     * @return a request for searching artists
     */
    public SearchDeezerRequest<Artist> searchArtist(AdvancedQuery query) {
        return searchArtist((Query) query);
    }

    private SearchDeezerRequest<Artist> searchArtist(Query query) {
        return createSearchDeezerRequest(query, searchService::searchArtist);
    }

    //endregion

    //region searchPlaylist

    /**
     * Creates a request to search for playlists based on a simple query string.
     *
     * @param query the search query string
     * @return a request for searching playlists
     */
    public SearchDeezerRequest<Playlist> searchPlaylist(String query) {
        return searchPlaylist(new SimpleQuery(query));
    }

    /**
     * Creates a request to search for playlists based on an advanced query.
     *
     * @param query the advanced search query
     * @return a request for searching playlists
     */
    public SearchDeezerRequest<Playlist> searchPlaylist(AdvancedQuery query) {
        return searchPlaylist((Query) query);
    }

    private SearchDeezerRequest<Playlist> searchPlaylist(Query query) {
        return createSearchDeezerRequest(query, searchService::searchPlaylist);
    }

    //endregion

    //region searchTrack

    /**
     * Creates a request to search for tracks based on a simple query string.
     *
     * @param query the search query string
     * @return a request for searching tracks
     */
    public SearchDeezerRequest<Track> searchTrack(String query) {
        return searchTrack(new SimpleQuery(query));
    }

    /**
     * Creates a request to search for tracks based on an advanced query.
     *
     * @param query the advanced search query
     * @return a request for searching tracks
     */
    public SearchDeezerRequest<Track> searchTrack(AdvancedQuery query) {
        return searchTrack((Query) query);
    }

    private SearchDeezerRequest<Track> searchTrack(Query query) {
        return createSearchDeezerRequest(query, searchService::searchTrack);
    }

    //endregion

    //region searchRadio

    /**
     * Creates a request to search for radios based on a simple query string.
     *
     * @param query the search query string
     * @return a request for searching radios
     */
    public SearchDeezerRequest<Radio> searchRadio(String query) {
        return searchRadio(new SimpleQuery(query));
    }

    /**
     * Creates a request to search for radios based on an advanced query.
     *
     * @param query the advanced search query
     * @return a request for searching radios
     */
    public SearchDeezerRequest<Radio> searchRadio(AdvancedQuery query) {
        return searchRadio((Query) query);
    }

    private SearchDeezerRequest<Radio> searchRadio(Query query) {
        return createSearchDeezerRequest(query, searchService::searchRadio);
    }

    //endregion

    //region searchUser

    /**
     * Creates a request to search for users based on a simple query string.
     *
     * @param query the search query string
     * @return a request for searching users
     */
    public SearchDeezerRequest<User> searchUser(String query) {
        return searchUser(new SimpleQuery(query));
    }

    /**
     * Creates a request to search for users based on an advanced query.
     *
     * @param query the advanced search query
     * @return a request for searching users
     */
    public SearchDeezerRequest<User> searchUser(AdvancedQuery query) {
        return searchUser((Query) query);
    }

    private SearchDeezerRequest<User> searchUser(Query query) {
        return createSearchDeezerRequest(query, searchService::searchUser);
    }

    //endregion

    private <T> SearchDeezerRequest<T> createSearchDeezerRequest(
            Query query,
            QuinaryFunction<Query, Boolean, Order, Integer, Integer, CompletableFuture<Page<T>>> asyncMethod
    ) {
        return new SearchDeezerRequest<>(query, asyncMethod);
    }
}
