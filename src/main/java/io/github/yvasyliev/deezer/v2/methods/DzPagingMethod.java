package io.github.yvasyliev.deezer.v2.methods;

import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.objects.Page;

public interface DzPagingMethod<T extends Pageable> extends DzMethod<Page<T, DzPagingMethod<T>>>, DzPageableMethod {
    DzPagingMethod<T> index(Integer index);

    DzPagingMethod<T> limit(Integer limit);
}
