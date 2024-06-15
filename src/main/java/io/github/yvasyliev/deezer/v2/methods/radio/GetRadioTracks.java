package io.github.yvasyliev.deezer.v2.methods.radio;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Track;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServicePagingMethod;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.util.concurrent.CompletableFuture;

public class GetRadioTracks extends AbstractObjectServicePagingMethod<Track, RadioService> {
    @Expose(serialize = false)
    @SerializedName(OBJECT_ID)
    protected final long objectId;
    protected final RadioService deezerService;
    @Expose
    @SerializedName(INDEX)
    private Integer index;
    @Expose
    @SerializedName(LIMIT)
    private Integer limit;

    public GetRadioTracks(Gson gson, RadioService radioService, long radioId) {
        super(gson, radioService, radioId);
    }

    @Override
    public Page<Track, PagingMethod<Track>> execute() {
        return deezerService.getRadioTracks(objectId, getQueryParams());
    }

    @Override
    public CompletableFuture<Page<Track, PagingMethod<Track>>> executeAsync() {
        return deezerService.getRadioTracksAsync(objectId, getQueryParams());
    }

    @Override
    public String toString() {
        return "/radio/" + objectId + "/tracks" + getQueryParams();
    }
}
