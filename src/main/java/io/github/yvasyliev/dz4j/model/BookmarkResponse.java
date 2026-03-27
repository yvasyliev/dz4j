package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response for bookmark operations.
 *
 * @param results indicates whether the bookmark operation was successful ({@code true}) or not ({@code false})
 */
public record BookmarkResponse(@JsonProperty("results") Boolean results) {}
