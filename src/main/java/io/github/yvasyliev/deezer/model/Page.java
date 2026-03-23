package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;
import lombok.Builder;
import lombok.Singular;

import java.net.URL;
import java.util.List;

/**
 * A page of results from the Deezer API, containing a list of data items and pagination information.
 *
 * @param data     the list of data items on this page
 * @param total    the total number of items available across all pages
 * @param checksum a checksum for the data on this page
 * @param next     the {@link URL} for the next page of results, or {@code null} if there are no more pages
 * @param prev     the {@link URL} for the previous page of results, or {@code null} if this is the first page
 * @param <T>      the type of data items contained in this page
 */
@Builder
public record Page<T>(
        @JsonProperty("data") @Singular("data") List<T> data,
        @JsonProperty("total") Integer total,
        @Experimental @JsonProperty("checksum") String checksum,
        @JsonProperty("next") URL next,
        @JsonProperty("prev") URL prev
) {}
