package io.github.yvasyliev.deezer.v2.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.methods.Method;
import lombok.Data;

import java.util.List;

@Data
public class Page<T extends Pageable, M extends Method<?>> {
    /**
     * List of objects.
     */
    @Expose
    @SerializedName("data")
    private List<T> data;

    /**
     * Total amount of objects.
     */
    @Expose
    @SerializedName("total")
    private Integer total;

    /**
     * Next page.
     */
    @Expose
    @SerializedName("next")
    private M next;

    /**
     * Previous page.
     */
    @Expose
    @SerializedName("prev")
    private M prev;

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrev() {
        return prev != null;
    }
}
