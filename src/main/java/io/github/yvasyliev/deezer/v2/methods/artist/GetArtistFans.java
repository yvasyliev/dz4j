package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzPagingIdMethod;
import io.github.yvasyliev.deezer.v2.methods.DzPagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetArtistFans extends AbstractDzPagingIdMethod<User, ArtistService> {
    public GetArtistFans(ArtistService artistService, Gson gson, long albumId) {
        super(artistService, gson, albumId);
    }

    @Override
    public CompletableFuture<Page<User, DzPagingMethod<User>>> executeAsync() {
        return deezerService.getArtistFansAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/artist/" + objectId + "/fans" + getQueryParams();
    }
}
