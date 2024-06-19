package io.github.yvasyliev.deezer.v2.methods;

import io.github.yvasyliev.deezer.objects.Order;
import io.github.yvasyliev.deezer.objects.Pageable;
import io.github.yvasyliev.deezer.v2.objects.Page;

import java.time.Duration;

public interface DzAdvancedSearchMethod<T extends Pageable> extends DzMethod<Page<T, DzAdvancedSearchMethod<T>>>, DzPageableMethod {
    DzAdvancedSearchMethod<T> index(Integer index);

    DzAdvancedSearchMethod<T> limit(Integer limit);

    DzAdvancedSearchMethod<T> order(Order order);

    DzAdvancedSearchMethod<T> strict(boolean strict);

    DzAdvancedSearchMethod<T> artist(String artist);

    DzAdvancedSearchMethod<T> track(String track);

    DzAdvancedSearchMethod<T> label(String label);

    DzAdvancedSearchMethod<T> minDuration(Duration minDuration);

    DzAdvancedSearchMethod<T> maxDuration(Duration maxDuration);

    DzAdvancedSearchMethod<T> minBpm(Integer minBpm);

    DzAdvancedSearchMethod<T> maxBpm(Integer maxBpm);
}
