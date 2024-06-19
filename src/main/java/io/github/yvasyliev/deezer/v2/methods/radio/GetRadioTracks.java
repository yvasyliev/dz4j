package io.github.yvasyliev.deezer.v2.methods.radio;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetRadioTracks extends AbstractDzPagingIdMethod<Track, RadioService> {
    public GetRadioTracks(RadioService deezerService, Gson gson, long objectId) {
        super(deezerService, gson, objectId);
    }

    @Override
    public CompletableFuture<Page<Track, DzPagingMethod<Track>>> executeAsync() {
        return deezerService.getRadioTracksAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/radio/" + objectId + "/tracks" + getQueryParams();
    }
}
