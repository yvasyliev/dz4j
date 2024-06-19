package io.github.yvasyliev.deezer.v2.methods.album;

import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzIdMethod;

import java.util.concurrent.CompletableFuture;

public class GetAlbum extends AbstractDzIdMethod<Album, AlbumService> {
    public GetAlbum(AlbumService albumService, long albumId) {
        super(albumService, albumId);
    }

    @Override
    public CompletableFuture<Album> executeAsync() {
        return deezerService.getAlbumAsync(objectId);
    }

    @Override
    public String toString() {
        return "/album/" + objectId;
    }
}
