package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.json.adapters.DurationAdapter;
import io.github.yvasyliev.deezer.v2.json.adapters.QAdapter;
import io.github.yvasyliev.deezer.v2.json.adapters.SearchStringAdapter;
import lombok.Setter;

import java.time.Duration;

public abstract class AdvancedSearchMethod<T extends Pageable> extends AbstractSearchMethod<T, AdvancedSearchMethod<T>> {
    @Expose
    @SerializedName("q")
    @JsonAdapter(QAdapter.class)
    private final Q q = new Q();

    public AdvancedSearchMethod(Gson gson, SearchService searchService) {
        super(gson, searchService);
    }

    public AdvancedSearchMethod<T> artist(String artist) {
        q.setArtist(artist);
        return getThis();
    }

    public AdvancedSearchMethod<T> album(String album) {
        q.setAlbum(album);
        return getThis();
    }

    public AdvancedSearchMethod<T> track(String track) {
        q.setTrack(track);
        return getThis();
    }

    public AdvancedSearchMethod<T> label(String label) {
        q.setLabel(label);
        return getThis();
    }

    public AdvancedSearchMethod<T> minDuration(Duration minDuration) {
        q.setMinDuration(minDuration);
        return getThis();
    }

    public AdvancedSearchMethod<T> maxDuration(Duration maxDuration) {
        q.setMaxDuration(maxDuration);
        return getThis();
    }

    public AdvancedSearchMethod<T> minBpm(int minBpm) {
        q.setMinBpm(minBpm);
        return getThis();
    }

    public AdvancedSearchMethod<T> maxBpm(int maxBpm) {
        q.setMaxBpm(maxBpm);
        return getThis();
    }

    @Override
    protected AdvancedSearchMethod<T> getThis() {
        return this;
    }

    @Setter
    public static class Q {
        @Expose
        @SerializedName("artist")
        @JsonAdapter(SearchStringAdapter.class)
        private String artist;

        @Expose
        @SerializedName("album")
        @JsonAdapter(SearchStringAdapter.class)
        private String album;

        @Expose
        @SerializedName("track")
        @JsonAdapter(SearchStringAdapter.class)
        private String track;

        @Expose
        @SerializedName("label")
        @JsonAdapter(SearchStringAdapter.class)
        private String label;

        @Expose
        @SerializedName("dur_min")
        @JsonAdapter(DurationAdapter.class)
        private Duration minDuration;

        @Expose
        @SerializedName("dur_max")
        @JsonAdapter(DurationAdapter.class)
        private Duration maxDuration;

        @Expose
        @SerializedName("bpm_min")
        private Integer minBpm;

        @Expose
        @SerializedName("bpm_max")
        private Integer maxBpm;
    }
}
