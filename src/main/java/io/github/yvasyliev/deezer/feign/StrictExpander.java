package io.github.yvasyliev.deezer.feign;

import feign.Param;

public class StrictExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        return Boolean.TRUE.equals(value) ? "on" : null;
    }
}
