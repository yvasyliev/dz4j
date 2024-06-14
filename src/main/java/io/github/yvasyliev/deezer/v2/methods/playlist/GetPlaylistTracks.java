package io.github.yvasyliev.deezer.v2.methods.playlist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetPlaylistTracks extends AbstractObjectServicePagingMethod<Track, PlaylistService> {
    public GetPlaylistTracks(Gson gson, PlaylistService playlistService, long playlistId) {
        super(gson, playlistService, playlistId);
    }

    @Override
    public Page<Track, PagingMethod<Track>> execute() {
        return deezerService.getPlaylistTracks(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, PagingMethod<Track>>> executeAsync() {
        return deezerService.getPlaylistTracksAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/playlist/" + objectId + "/tracks" + getQueryParams();
    }
}
