package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistRadio extends AbstractObjectServicePagingMethod<Track, ArtistService> {
    public GetArtistRadio(Gson gson, ArtistService artistService, long artistId) {
        super(gson, artistService, artistId);
    }

    @Override
    public Page<Track, PagingMethod<Track>> execute() {
        return deezerService.getArtistRadio(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, PagingMethod<Track>>> executeAsync() {
        return deezerService.getArtistRadioAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/radio" + getQueryParams();
    }
}
