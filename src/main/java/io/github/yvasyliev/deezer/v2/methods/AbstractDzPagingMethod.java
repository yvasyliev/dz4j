package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Type;
import java.util.Map;

@RequiredArgsConstructor
@Setter
@Accessors(fluent = true)
public abstract class AbstractDzPagingMethod<T extends Pageable, S extends DeezerService> implements DzPagingMethod<T> {
    private static final Type QUERY_PARAMS_TYPE = new TypeToken<Map<String, Object>>() {
    }.getType();

    protected final S deezerService;

    private final Gson gson;

    @Expose
    @SerializedName("index")
    private Integer index;

    @Expose
    @SerializedName("limit")
    private Integer limit;

    protected Map<String, Object> getQueryParams() {
        return gson.fromJson(
                gson.toJsonTree(this),
                QUERY_PARAMS_TYPE
        );
    }
}
