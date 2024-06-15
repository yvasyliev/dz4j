package io.github.yvasyliev.deezer.v2.methods.album;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.factories.QueryParamsFactory;
import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public class GetAlbumFans extends AbstractDzMethod<Page<User, GetAlbumFans>> {
    private final AlbumService albumService;

    private final QueryParamsFactory queryParamsFactory;

    @Expose(serialize = false)
    @SerializedName(OBJECT_ID)
    private final long albumId;

    @Expose
    @SerializedName(INDEX)
    private Integer index;

    @Expose
    @SerializedName(LIMIT)
    private Integer limit;

    @Override
    public CompletableFuture<Page<User, GetAlbumFans>> executeAsync() {
        return albumService.getAlbumFansAsync(albumId, getQueryParams());
    }

    @Override
    protected Map<String, Object> getQueryParams() {
        return queryParamsFactory.getQueryParams(this);
    }

    @Override
    public String toString() {
        return "/album/" + albumId + "/fans" + getQueryParams();
    }
}
