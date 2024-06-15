package io.github.yvasyliev.deezer.v2.methods;

import java.util.Map;

public abstract class AbstractMethod<T> implements Method<T> {
    protected abstract Map<String, Object> getQueryParams();
}
