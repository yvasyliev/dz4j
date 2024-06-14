package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.SearchService;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class SearchMethod<T extends Pageable> extends AbstractSearchMethod<T, SearchMethod<T>> {
    @Expose
    @SerializedName("q")
    private final String q;

    public SearchMethod(Gson gson, SearchService searchService, String q) {
        super(gson, searchService);
        this.q = q;
    }

    @Override
    protected SearchMethod<T> getThis() {
        return this;
    }
}
