package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public abstract class AbstractQueryMethod<T> implements Method<T> {
    private static final TypeToken<Map<String, Object>> QUERY_PARAMS_TYPE_TOKEN = new TypeToken<Map<String, Object>>() {
    };

    private final Gson gson;

    protected Map<String, Object> getQueryParams() {
        return gson.fromJson(
                gson.toJsonTree(this),
                QUERY_PARAMS_TYPE_TOKEN.getType()
        );
    }
}
