package io.github.yvasyliev.deezer.factories;

import java.util.Map;

@FunctionalInterface
public interface QueryParamsFactory {
    Map<String, Object> getQueryParams(Object src);
}
