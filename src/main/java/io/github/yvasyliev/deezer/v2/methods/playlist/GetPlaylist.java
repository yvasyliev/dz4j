package io.github.yvasyliev.deezer.v2.methods.playlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetPlaylist extends AbstractObjectServiceMethod<Playlist, PlaylistService> {
    @Expose
    @SerializedName(OBJECT_ID)
    protected final long objectId;

    public GetPlaylist(PlaylistService playlistService, long playlistId) {
        super(playlistService, playlistId);
    }

    @Override
    public Playlist execute() {
        return deezerService.getPlaylist(objectId);
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
