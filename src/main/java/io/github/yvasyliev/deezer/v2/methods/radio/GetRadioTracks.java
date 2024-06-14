package io.github.yvasyliev.deezer.v2.methods.radio;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetRadioTracks extends AbstractObjectServicePagingMethod<Track, RadioService> {
    public GetRadioTracks(Gson gson, RadioService radioService, long radioId) {
        super(gson, radioService, radioId);
    }

    @Override
    public Page<Track, PagingMethod<Track>> execute() {
        return deezerService.getRadioTracks(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, PagingMethod<Track>>> executeAsync() {
        return deezerService.getRadioTracksAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/radio/" + objectId + "/tracks" + getQueryParams();
    }
}
