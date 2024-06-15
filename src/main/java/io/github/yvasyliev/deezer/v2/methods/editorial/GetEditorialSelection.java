package io.github.yvasyliev.deezer.v2.methods.editorial;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Album;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetEditorialSelection extends AbstractObjectServicePagingMethod<Album, EditorialService> {
    @Expose(serialize = false)
    @SerializedName(OBJECT_ID)
    protected final long objectId;
    protected final EditorialService deezerService;
    @Expose
    @SerializedName(INDEX)
    private Integer index;
    @Expose
    @SerializedName(LIMIT)
    private Integer limit;

    public GetEditorialSelection(Gson gson, EditorialService editorialService, long editorialId) {
        super(gson, editorialService, editorialId);
    }

    @Override
    public Page<Album, PagingMethod<Album>> execute() {
        return deezerService.getEditorialSelection(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Album, PagingMethod<Album>>> executeAsync() {
        return deezerService.getEditorialSelectionAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/editorial/" + objectId + "/selection" + getQueryParams();
    }
}
