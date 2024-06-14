package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetAlbumTracks extends AbstractObjectServicePagingMethod<Track, AlbumService> {
    public GetAlbumTracks(Gson gson, AlbumService albumService, long albumId) {
        super(gson, albumService, albumId);
    }

    @Override
    public Page<Track, PagingMethod<Track>> execute() {
        return deezerService.getAlbumTracks(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, PagingMethod<Track>>> executeAsync() {
        return deezerService.getAlbumTracksAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/album/" + objectId + "/tracks" + getQueryParams();
    }
}
