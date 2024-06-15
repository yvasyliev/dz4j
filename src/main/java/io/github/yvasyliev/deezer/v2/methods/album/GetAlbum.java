package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzMethod;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class GetAlbum extends AbstractDzMethod<Album> {
    private final AlbumService albumService;

    @Expose
    @SerializedName(OBJECT_ID)
    private final long albumId;

    @Override
    public CompletableFuture<Album> executeAsync() {
        return albumService.getAlbumAsync(albumId);
    }

    @Override
    public String toString() {
        return "/album/" + albumId;
    }
}
