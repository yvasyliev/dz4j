package io.github.yvasyliev.dz4j.model;

/**
 * A query object, which can be either a simple query or an advanced query.
 */
public sealed interface Query permits AdvancedQuery, SimpleQuery {}
