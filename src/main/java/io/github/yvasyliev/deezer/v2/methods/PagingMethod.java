package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.objects.Pageable;

public abstract class PagingMethod<T extends Pageable> extends AbstractPagingMethod<T, PagingMethod<T>> {
    public PagingMethod(Gson gson) {
        super(gson);
    }

    @Override
    protected PagingMethod<T> getThis() {
        return this;
    }
}
