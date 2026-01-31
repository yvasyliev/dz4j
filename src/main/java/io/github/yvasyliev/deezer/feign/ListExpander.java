package io.github.yvasyliev.deezer.feign;

import feign.Param;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        if (value instanceof Collection<?> collection) {
            return collection.stream()
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
        }

        throw new IllegalArgumentException("ListExpander expects Collection type, but got: " + value.getClass());
    }
}
