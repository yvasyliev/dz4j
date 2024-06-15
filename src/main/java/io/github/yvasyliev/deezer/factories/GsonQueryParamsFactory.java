package io.github.yvasyliev.deezer.factories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.Map;

@RequiredArgsConstructor
public class GsonQueryParamsFactory implements QueryParamsFactory {
    private static final Type QUERY_PARAMS_TYPE = new TypeToken<Map<String, Object>>() {
    }.getType();

    private final Gson gson;

    @Override
    public Map<String, Object> getQueryParams(Object src) {
        return gson.fromJson(
                gson.toJsonTree(src),
                QUERY_PARAMS_TYPE
        );
    }
}
