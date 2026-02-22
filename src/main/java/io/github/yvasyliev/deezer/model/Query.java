package io.github.yvasyliev.deezer.model;

public sealed interface Query permits AdvancedQuery, SimpleQuery {}
