package io.github.yvasyliev.dz4j.model;

/**
 * A simple query object, which contains a single query string.
 *
 * @param query the query string
 */
public record SimpleQuery(String query) implements Query {}
