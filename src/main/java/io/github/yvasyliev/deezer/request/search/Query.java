package io.github.yvasyliev.deezer.request.search;

public sealed interface Query permits AdvancedQuery, SimpleQuery {}
