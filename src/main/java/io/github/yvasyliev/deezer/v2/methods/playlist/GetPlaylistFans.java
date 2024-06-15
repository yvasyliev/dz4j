package io.github.yvasyliev.deezer.v2.methods.playlist;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetPlaylistFans extends AbstractObjectServicePagingMethod<User, PlaylistService> {
    @Expose(serialize = false)
    @SerializedName(OBJECT_ID)
    protected final long objectId;
    protected final PlaylistService deezerService;
    @Expose
    @SerializedName(INDEX)
    private Integer index;
    @Expose
    @SerializedName(LIMIT)
    private Integer limit;

    public GetPlaylistFans(Gson gson, PlaylistService playlistService, long playlistId) {
        super(gson, playlistService, playlistId);
    }

    @Override
    public Page<User, PagingMethod<User>> execute() {
        return deezerService.getPlaylistFans(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<User, PagingMethod<User>>> executeAsync() {
        return deezerService.getPlaylistFansAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/playlist/" + objectId + "/fans" + getQueryParams();
    }
}
