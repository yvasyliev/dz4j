package io.github.yvasyliev.deezer.model;

/**
 * A simple query object, which contains a single query string.
 *
 * @param query the query string
 */
public record SimpleQuery(String query) implements Query {}
