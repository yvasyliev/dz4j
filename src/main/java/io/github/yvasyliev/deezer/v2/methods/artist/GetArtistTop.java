package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistTop extends AbstractDzPagingIdMethod<Track, ArtistService> {
    public GetArtistTop(ArtistService artistService, Gson gson, long albumId) {
        super(artistService, gson, albumId);
    }

    @Override
    public CompletableFuture<Page<Track, DzPagingMethod<Track>>> executeAsync() {
        return deezerService.getArtistTopAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/top" + getQueryParams();
    }
}
