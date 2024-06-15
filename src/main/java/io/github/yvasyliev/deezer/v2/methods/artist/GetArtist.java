package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Artist;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzMethod;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class GetArtist extends AbstractDzMethod<Artist> {
    private final ArtistService artistService;

    @Expose
    @SerializedName(OBJECT_ID)
    protected final long artistId;

    @Override
    public CompletableFuture<Artist> executeAsync() {
        return artistService.getArtistAsync(artistId);
    }

    @Override
    public String toString() {
        return "/artist/" + artistId;
    }
}
