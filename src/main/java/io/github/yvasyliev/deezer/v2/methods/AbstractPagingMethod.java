package io.github.yvasyliev.deezer.v2.methods;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.objects.Page;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public abstract class AbstractPagingMethod<T extends Pageable, M extends AbstractPagingMethod<T, M>> extends AbstractQueryMethod<Page<T, M>> {
    @Expose
    @SerializedName("index")
    private Integer index;

    @Expose
    @SerializedName("limit")
    private Integer limit;

    public AbstractPagingMethod(Gson gson) {
        super(gson);
    }

    public M index(int index) {
        this.index = index;
        return getThis();
    }

    public M limit(int limit) {
        this.limit = limit;
        return getThis();
    }

    protected abstract M getThis();
}
