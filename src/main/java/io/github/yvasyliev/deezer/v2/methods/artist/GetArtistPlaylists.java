package io.github.yvasyliev.deezer.v2.methods.artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.factories.QueryParamsFactory;
import io.github.yvasyliev.deezer.objects.Playlist;
import io.github.yvasyliev.deezer.service.ArtistService;
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
public class GetArtistPlaylists extends AbstractDzMethod<Page<Playlist, GetArtistPlaylists>> {
    private final ArtistService artistService;

    private final QueryParamsFactory queryParamsFactory;

    @Expose(serialize = false)
    @SerializedName(OBJECT_ID)
    protected final long artistId;

    @Expose
    @SerializedName(INDEX)
    private Integer index;

    @Expose
    @SerializedName(LIMIT)
    private Integer limit;

    @Override
    public CompletableFuture<Page<Playlist, GetArtistPlaylists>> executeAsync() {
        return artistService.getArtistPlaylistsAsync(artistId, getQueryParams());
    }

    @Override
    protected Map<String, Object> getQueryParams() {
        return queryParamsFactory.getQueryParams(this);
    }

    @Override
    public String toString() {
        return "/artist/" + artistId + "/playlists" + getQueryParams();
    }
}
