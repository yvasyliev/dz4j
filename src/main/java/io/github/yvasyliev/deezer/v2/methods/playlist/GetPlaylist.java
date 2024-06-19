package io.github.yvasyliev.deezer.v2.methods.playlist;

import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzIdMethod;

import java.util.concurrent.CompletableFuture;

public class GetPlaylist extends AbstractDzIdMethod<Playlist, PlaylistService> {

    public GetPlaylist(PlaylistService playlistService, long playlistId) {
        super(playlistService, playlistId);
    }

    @Override
    public CompletableFuture<Playlist> executeAsync() {
        return deezerService.getPlaylistAsync(objectId);
    }

    @Override
    public String toString() {
        return "/playlist/" + objectId;
    }
}
