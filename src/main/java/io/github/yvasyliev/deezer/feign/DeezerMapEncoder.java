package io.github.yvasyliev.deezer.feign;

import feign.QueryMapEncoder;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;

@RequiredArgsConstructor
public class DeezerMapEncoder implements QueryMapEncoder {
    private static final TypeReference<Map<String, Object>> QUERY_MAP_TYPE = new TypeReference<>() {};
    private final JsonMapper jsonMapper;

    @Override
    public Map<String, Object> encode(Object object) {
        return jsonMapper.convertValue(object, QUERY_MAP_TYPE); //TODO: allow POJO only
    }
}
