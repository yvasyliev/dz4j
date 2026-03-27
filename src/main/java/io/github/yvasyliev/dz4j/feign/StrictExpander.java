package io.github.yvasyliev.dz4j.feign;

import feign.Param;
import org.jspecify.annotations.Nullable;

/**
 * A strict expander for boolean values.
 */
public class StrictExpander implements Param.Expander {
    /**
     * Expands a boolean value to {@code on} if the value is {@code true}, and to {@code null} if the value is
     * {@code false}.
     *
     * @param value the value to expand
     * @return {@code on} if the value is {@code true}, and {@code null} if the value is {@code false}
     */
    @Override
    @Nullable
    public String expand(Object value) {
        return Boolean.TRUE.equals(value) ? "on" : null;
    }
}
