package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistAlbums extends AbstractObjectServicePagingMethod<Album, ArtistService> {
    public GetArtistAlbums(Gson gson, ArtistService artistService, long artistId) {
        super(gson, artistService, artistId);
    }

    @Override
    public Page<Album, PagingMethod<Album>> execute() {
        return deezerService.getArtistAlbums(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Album, PagingMethod<Album>>> executeAsync() {
        return deezerService.getArtistAlbumsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/albums" + getQueryParams();
    }
}
