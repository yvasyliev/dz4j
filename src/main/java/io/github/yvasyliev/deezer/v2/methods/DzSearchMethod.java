package io.github.yvasyliev.deezer.v2.methods;

import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.objects.Page;

public interface DzSearchMethod<T extends Pageable> extends DzMethod<Page<T, DzSearchMethod<T>>>, DzPageableMethod {
    DzSearchMethod<T> index(Integer index);

    DzSearchMethod<T> limit(Integer limit);

    DzSearchMethod<T> order(Order order);

    DzSearchMethod<T> strict(boolean strict);
}
