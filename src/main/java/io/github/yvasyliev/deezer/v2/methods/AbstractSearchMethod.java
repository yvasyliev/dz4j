package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.service.SearchService;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class AbstractSearchMethod<T extends Pageable, M extends AbstractSearchMethod<T, M>> extends AbstractPagingMethod<T, M> {
    private static final String STRICT_ON = "on";

    @Expose
    @SerializedName("strict")
    private String strict;

    public AbstractSearchMethod(ArtistService gson, SearchService searchService) {
        super(gson);
        this.searchService = searchService;
    }

    public M strict(boolean strict) {
        if (strict) {
            this.strict = STRICT_ON;
        }
        return getThis();
    }

    public M order(Order order) {
        this.order = order;
        return getThis();
    }
}
