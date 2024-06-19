package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetAlbumTracks extends AbstractDzPagingIdMethod<Track, AlbumService> {
    public GetAlbumTracks(AlbumService albumService, Gson gson, long albumId) {
        super(albumService, gson, albumId);
    }

    @Override
    public CompletableFuture<Page<Track, DzPagingMethod<Track>>> executeAsync() {
        return deezerService.getAlbumTracksAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/album/" + objectId + "/tracks" + getQueryParams();
    }
}
