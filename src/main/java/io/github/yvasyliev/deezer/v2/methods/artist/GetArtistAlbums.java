package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistAlbums extends AbstractDzPagingIdMethod<Album, ArtistService> {
    public GetArtistAlbums(ArtistService artistService, Gson gson, long albumId) {
        super(artistService, gson, albumId);
    }

    @Override
    public CompletableFuture<Page<Album, DzPagingMethod<Album>>> executeAsync() {
        return deezerService.getArtistAlbumsAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/albums" + getQueryParams();
    }
}
