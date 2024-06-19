package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistRelated extends AbstractDzPagingIdMethod<Artist, ArtistService> {
    public GetArtistRelated(ArtistService artistService, Gson gson, long albumId) {
        super(artistService, gson, albumId);
    }

    @Override
    public CompletableFuture<Page<Artist, DzPagingMethod<Artist>>> executeAsync() {
        return deezerService.getArtistRelatedAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/related" + getQueryParams();
    }
}
