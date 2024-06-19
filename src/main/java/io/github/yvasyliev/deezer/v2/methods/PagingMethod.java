package io.github.yvasyliev.deezer.v2.methods;

import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.ArtistService;

public abstract class PagingMethod<T extends Pageable> extends AbstractPagingMethod<T, PagingMethod<T>> {

    public PagingMethod(ArtistService gson) {
        super(gson);
    }

    @Override
    protected PagingMethod<T> getThis() {
        return this;
    }
}
