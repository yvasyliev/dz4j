package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.feign.QueryExpander;
import io.github.yvasyliev.deezer.feign.StrictExpander;
import io.github.yvasyliev.deezer.model.Album;
import io.github.yvasyliev.deezer.model.Artist;
import io.github.yvasyliev.deezer.request.search.Order;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.model.Playlist;
import io.github.yvasyliev.deezer.request.search.Query;
import io.github.yvasyliev.deezer.model.Track;
import io.github.yvasyliev.deezer.model.User;

import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface SearchService {
    /**
     * Searches for tracks matching the given query. An alias for
     * {@link #searchTrack(Query, Boolean, Order, Integer, Integer)}.
     *
     * @param query  search query
     * @param strict if {@code true}, enables strict mode for more accurate results
     * @param order  order of the results
     * @param index  index of the first result to return
     * @param limit  maximum number of results to return
     * @return a page of tracks matching the search criteria
     */
    @RequestLine("GET /search?q={query}&strict={strict}&order={order}&index={index}&limit={limit}")
    CompletableFuture<Page<Track>> search(
            @Param(value = "query", expander = QueryExpander.class) Query query,
            @Param(value = "strict", expander = StrictExpander.class) Boolean strict,
            @Param("order") Order order,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Searches for albums matching the given query.
     *
     * @param query  search query
     * @param strict if {@code true}, enables strict mode for more accurate results
     * @param order  order of the results
     * @param index  index of the first result to return
     * @param limit  maximum number of results to return
     * @return a page of albums matching the search criteria
     */
    @RequestLine("GET /search/album?q={query}&strict={strict}&order={order}&index={index}&limit={limit}")
    CompletableFuture<Page<Album>> searchAlbum(
            @Param(value = "query", expander = QueryExpander.class) Query query,
            @Param(value = "strict", expander = StrictExpander.class) Boolean strict,
            @Param("order") Order order,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Searches for artists matching the given query.
     *
     * @param query  search query
     * @param strict if {@code true}, enables strict mode for more accurate results
     * @param order  order of the results
     * @param index  index of the first result to return
     * @param limit  maximum number of results to return
     * @return a page of artists matching the search criteria
     */
    @RequestLine("GET /search/artist?q={query}&strict={strict}&order={order}&index={index}&limit={limit}")
    CompletableFuture<Page<Artist>> searchArtist(
            @Param(value = "query", expander = QueryExpander.class) Query query,
            @Param(value = "strict", expander = StrictExpander.class) Boolean strict,
            @Param("order") Order order,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    //TODO: search history & search radio

    /**
     * Searches for playlists matching the given query.
     *
     * @param query  search query
     * @param strict if {@code true}, enables strict mode for more accurate results
     * @param order  order of the results
     * @param index  index of the first result to return
     * @param limit  maximum number of results to return
     * @return a page of playlists matching the search criteria
     */
    @RequestLine("GET /search/playlist?q={query}&strict={strict}&order={order}&index={index}&limit={limit}")
    CompletableFuture<Page<Playlist>> searchPlaylist(
            @Param(value = "query", expander = QueryExpander.class) Query query,
            @Param(value = "strict", expander = StrictExpander.class) Boolean strict,
            @Param("order") Order order,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Searches for tracks matching the given query. An alias for
     * {@link #search(Query, Boolean, Order, Integer, Integer)}.
     *
     * @param query  search query
     * @param strict if {@code true}, enables strict mode for more accurate results
     * @param order  order of the results
     * @param index  index of the first result to return
     * @param limit  maximum number of results to return
     * @return a page of tracks matching the search criteria
     */
    @RequestLine("GET /search/track?q={query}&strict={strict}&order={order}&index={index}&limit={limit}")
    CompletableFuture<Page<Track>> searchTrack(
            @Param(value = "query", expander = QueryExpander.class) Query query,
            @Param(value = "strict", expander = StrictExpander.class) Boolean strict,
            @Param("order") Order order,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );

    /**
     * Searches for users matching the given query.
     *
     * @param query  search query
     * @param strict if {@code true}, enables strict mode for more accurate results
     * @param order  order of the results
     * @param index  index of the first result to return
     * @param limit  maximum number of results to return
     * @return a page of users matching the search criteria
     */
    @RequestLine("GET /search/user?q={query}&strict={strict}&order={order}&index={index}&limit={limit}")
    CompletableFuture<Page<User>> searchUser(
            @Param(value = "query", expander = QueryExpander.class) Query query,
            @Param(value = "strict", expander = StrictExpander.class) Boolean strict,
            @Param("order") Order order,
            @Param("index") Integer index,
            @Param("limit") Integer limit
    );
}
