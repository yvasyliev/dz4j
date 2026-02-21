package io.github.yvasyliev.deezer.request;

public interface PagingDeezerRequest<T> extends DeezerRequest<T> {
    PagingDeezerRequest<T> index(Integer index);

    PagingDeezerRequest<T> limit(Integer limit);
}
