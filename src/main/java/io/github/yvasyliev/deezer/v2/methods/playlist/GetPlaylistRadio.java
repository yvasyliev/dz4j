package io.github.yvasyliev.deezer.v2.methods.playlist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetPlaylistRadio extends AbstractDzPagingIdMethod<Track, PlaylistService> {
    public GetPlaylistRadio(PlaylistService deezerService, Gson gson, long objectId) {
        super(deezerService, gson, objectId);
    }

    @Override
    public CompletableFuture<Page<Track, DzPagingMethod<Track>>> executeAsync() {
        return deezerService.getPlaylistRadioAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/playlist/" + objectId + "/radio" + getQueryParams();
    }
}
