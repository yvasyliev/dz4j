package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.model.AdvancedQuery;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.model.Query;
import io.github.yvasyliev.deezer.model.SimpleQuery;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;
import io.github.yvasyliev.deezer.request.SearchDeezerRequest;
import io.github.yvasyliev.deezer.service.SearchService;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating search requests.
 */
@RequiredArgsConstructor
public class SearchRequestFactory {
    private final SearchService searchService;

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

    private SearchDeezerRequest<Track> search(Query query) {
        return new SearchDeezerRequest<>(query, searchService::search);
    }

    private SearchDeezerRequest<Album> searchAlbum(Query query) {
        return new SearchDeezerRequest<>(query, searchService::searchAlbum);
    }

    private SearchDeezerRequest<Artist> searchArtist(Query query) {
        return new SearchDeezerRequest<>(query, searchService::searchArtist);
    }

    private SearchDeezerRequest<Playlist> searchPlaylist(Query query) {
        return new SearchDeezerRequest<>(query, searchService::searchPlaylist);
    }

    private SearchDeezerRequest<Track> searchTrack(Query query) {
        return new SearchDeezerRequest<>(query, searchService::searchTrack);
    }

    private SearchDeezerRequest<User> searchUser(Query query) {
        return new SearchDeezerRequest<>(query, searchService::searchUser);
    }
}
