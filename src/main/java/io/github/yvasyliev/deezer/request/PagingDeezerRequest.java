package io.github.yvasyliev.deezer.request;

import org.jspecify.annotations.Nullable;

/**
 * Represents a request to the Deezer API that supports pagination parameters.
 *
 * @param <T> the type of the response expected from the Deezer API
 */
public interface PagingDeezerRequest<T> extends DeezerRequest<T> {
    /**
     * Sets the index of the first item to return in the response.
     *
     * @param index the index of the first item to return in the response
     * @return the updated request with the specified index parameter
     */
    PagingDeezerRequest<T> index(@Nullable Integer index);

    /**
     * Sets the maximum number of items to return in the response.
     *
     * @param limit the maximum number of items to return in the response
     * @return the updated request with the specified limit parameter
     */
    PagingDeezerRequest<T> limit(@Nullable Integer limit);
}
