package io.github.yvasyliev.deezer.v2.methods;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractDzMethod<T> {
    protected static final String OBJECT_ID = "objectId";
    protected static final String INDEX = "index";
    protected static final String LIMIT = "limit";

    public abstract CompletableFuture<T> executeAsync();

    public T execute() {
        return executeAsync().join();
    }

    protected Map<String, Object> getQueryParams() {
        return Collections.emptyMap();
    }
}
