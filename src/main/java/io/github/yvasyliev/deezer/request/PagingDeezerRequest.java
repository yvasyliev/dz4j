package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.model.Page;

public interface PagingDeezerRequest<T> extends DeezerRequest<Page<T>> {
    PagingDeezerRequest<T> index(Integer index);

    PagingDeezerRequest<T> limit(Integer limit);
}
