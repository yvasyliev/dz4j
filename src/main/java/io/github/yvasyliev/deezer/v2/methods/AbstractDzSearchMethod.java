package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Type;
import java.util.Map;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public abstract class AbstractDzSearchMethod<T extends Pageable> implements DzSearchMethod<T> {
    private static final Type QUERY_PARAMS_TYPE = new TypeToken<Map<String, Object>>() {
    }.getType();

    private static final String STRICT_ON = "on";

    protected final SearchService searchService;

    private final Gson gson;

    @Expose
    @SerializedName("q")
    private final String q;

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
    public AbstractDzSearchMethod<T> strict(boolean strict) {
        if (strict) {
            this.strict = STRICT_ON;
        }
        return this;
    }

    protected Map<String, Object> getQueryParams() {
        return gson.fromJson(
                gson.toJsonTree(this),
                QUERY_PARAMS_TYPE
        );
    }
}
