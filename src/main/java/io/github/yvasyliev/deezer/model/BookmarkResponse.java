package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookmarkResponse(@JsonProperty("results") Boolean results) {}
