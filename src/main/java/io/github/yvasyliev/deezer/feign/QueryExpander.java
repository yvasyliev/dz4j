package io.github.yvasyliev.deezer.feign;

import feign.Param;
import io.github.yvasyliev.deezer.model.AdvancedQuery;
import io.github.yvasyliev.deezer.model.SimpleQuery;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link io.github.yvasyliev.deezer.model.Query} expander for Feign clients.
 */
@RequiredArgsConstructor
public class QueryExpander implements Param.Expander {
    private static final TypeReference<Map<String, String>> QUERY_MAP_TYPE = new TypeReference<>() {};
    private final JsonMapper jsonMapper;

    /**
     * Expands {@link io.github.yvasyliev.deezer.model.Query} into a string representation suitable for Feign requests.
     *
     * @param value a value to expand
     * @return the expanded string representation of the query, or {@code null} if the value is not a supported query
     *         type
     */
    @Override
    @Nullable
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
