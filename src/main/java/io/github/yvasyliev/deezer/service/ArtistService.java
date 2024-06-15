package io.github.yvasyliev.deezer.service;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistAlbums;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistFans;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistPlaylists;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistRadio;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistRelated;
import io.github.yvasyliev.deezer.v2.methods.artist.GetArtistTop;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ArtistService extends DeezerService {
    String ARTIST = "/artist/{artistId}";
    String ARTIST_ALBUMS = "/artist/{artistId}/albums";
    String ARTIST_FANS = "/artist/{artistId}/fans";
    String ARTIST_PLAYLISTS = "/artist/{artistId}/playlists";
    String ARTIST_RADIO = "/artist/{artistId}/radio";
    String ARTIST_RELATED = "/artist/{artistId}/related";
    String ARTIST_TOP = "/artist/{artistId}/top";

    @RequestLine(GET + ARTIST)
    CompletableFuture<Artist> getArtistAsync(@Param("artistId") long artistId);

    @RequestLine(GET + ARTIST_ALBUMS)
    CompletableFuture<Page<Album, GetArtistAlbums>> getArtistAlbumsAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_FANS)
    CompletableFuture<Page<User, GetArtistFans>> getArtistFansAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_PLAYLISTS)
    CompletableFuture<Page<Playlist, GetArtistPlaylists>> getArtistPlaylistsAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_RADIO)
    CompletableFuture<Page<Track, GetArtistRadio>> getArtistRadioAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_RELATED)
    CompletableFuture<Page<Artist, GetArtistRelated>> getArtistRelatedAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);

    @RequestLine(GET + ARTIST_TOP)
    CompletableFuture<Page<Track, GetArtistTop>> getArtistTopAsync(@Param("artistId") long artistId, @QueryMap Map<String, Object> queryParams);
}
