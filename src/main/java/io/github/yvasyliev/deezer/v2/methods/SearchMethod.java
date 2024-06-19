package io.github.yvasyliev.deezer.v2.methods;

import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.service.SearchService;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class SearchMethod<T extends Pageable> extends AbstractSearchMethod<T, SearchMethod<T>> {

    public SearchMethod(ArtistService gson, SearchService searchService, String q) {
        super(gson, searchService);
        this.q = q;
    }

    @Override
    protected SearchMethod<T> getThis() {
        return this;
    }
}
