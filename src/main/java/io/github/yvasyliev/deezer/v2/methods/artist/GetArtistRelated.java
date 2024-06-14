package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistRelated extends AbstractObjectServicePagingMethod<Artist, ArtistService> {
    public GetArtistRelated(Gson gson, ArtistService artistService, long artistId) {
        super(gson, artistService, artistId);
    }

    @Override
    public Page<Artist, PagingMethod<Artist>> execute() {
        return deezerService.getArtistRelated(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Artist, PagingMethod<Artist>>> executeAsync() {
        return deezerService.getArtistRelatedAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/related" + getQueryParams();
    }
}
