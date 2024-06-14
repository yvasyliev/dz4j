package io.github.yvasyliev.deezer.v2.methods.album;

import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetAlbum extends AbstractObjectServiceMethod<Album, AlbumService> {
    public GetAlbum(AlbumService albumService, long albumId) {
        super(albumService, albumId);
    }

    @Override
    public Album execute() {
        return deezerService.getAlbum(objectId);
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
