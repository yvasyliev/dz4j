package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistPlaylists extends AbstractDzPagingIdMethod<Playlist, ArtistService> {
    public GetArtistPlaylists(ArtistService artistService, Gson gson, long albumId) {
        super(artistService, gson, albumId);
    }

    @Override
    public CompletableFuture<Page<Playlist, DzPagingMethod<Playlist>>> executeAsync() {
        return deezerService.getArtistPlaylistsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/playlists" + getQueryParams();
    }
}
