package io.github.yvasyliev.deezer.feign;

import feign.Param;
import io.github.yvasyliev.deezer.model.AdvancedQuery;
import io.github.yvasyliev.deezer.model.SimpleQuery;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QueryExpander implements Param.Expander {
    private static final TypeReference<Map<String, String>> QUERY_MAP_TYPE = new TypeReference<>() {};
    private final JsonMapper jsonMapper;

    @Override
    public String expand(Object value) {
        if (value instanceof SimpleQuery query) {
            return query.query();
        }

        if (value instanceof AdvancedQuery query) {
            return expand(query);
        }

        return null;
    }

    private String expand(AdvancedQuery query) {
        return jsonMapper.convertValue(query, QUERY_MAP_TYPE)
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(" "));
    }
}
