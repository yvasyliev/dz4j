package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.v2.json.adapters.DurationAdapter;
import io.github.yvasyliev.deezer.v2.json.adapters.SearchStringAdapter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Map;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public abstract class AbstractDzAdvancedSearchMethod<T extends Pageable> implements DzAdvancedSearchMethod<T> {
    private static final Type QUERY_PARAMS_TYPE = new TypeToken<Map<String, Object>>() {
    }.getType();

    private static final String STRICT_ON = "on";

    protected final SearchService searchService;

    private final Gson gson;

    @Expose
    @SerializedName("q")
    private final Q q = new Q();

    @Expose
    @SerializedName("index")
    private Integer index;

    @Expose
    @SerializedName("limit")
    private Integer limit;

    @Expose
    @SerializedName("order")
    private Order order;

    @Expose
    @SerializedName("strict")
    private String strict;

    @Override
    public AbstractDzAdvancedSearchMethod<T> strict(boolean strict) {
        if (strict) {
            this.strict = STRICT_ON;
        }
        return this;
    }

    @Override
    public AbstractDzAdvancedSearchMethod<T> artist(String artist) {
        q.artist = artist;
        return this;
    }

    @Override
    public AbstractDzAdvancedSearchMethod<T> track(String track) {
        q.track = track;
        return this;
    }

    @Override
    public AbstractDzAdvancedSearchMethod<T> label(String label) {
        q.label = label;
        return this;
    }

    @Override
    public AbstractDzAdvancedSearchMethod<T> minDuration(Duration minDuration) {
        q.minDuration = minDuration;
        return this;
    }

    @Override
    public AbstractDzAdvancedSearchMethod<T> maxDuration(Duration maxDuration) {
        q.maxDuration = maxDuration;
        return this;
    }

    @Override
    public AbstractDzAdvancedSearchMethod<T> minBpm(Integer minBpm) {
        q.minBpm = minBpm;
        return this;
    }

    @Override
    public AbstractDzAdvancedSearchMethod<T> maxBpm(Integer maxBpm) {
        q.maxBpm = maxBpm;
        return this;
    }

    protected Map<String, Object> getQueryParams() {
        return gson.fromJson(
                gson.toJsonTree(this),
                QUERY_PARAMS_TYPE
        );
    }
    
    private static class Q {
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
