package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;

@Experimental
public enum Role {
    @JsonProperty("Main") MAIN,
    @JsonProperty("Featured") FEATURED,
    @JsonProperty("Remixer") REMIXER,
    @JsonProperty("Composer") COMPOSER,
    @JsonProperty("Author") AUTHOR,
    @JsonProperty("Producer") PRODUCER,
    @JsonProperty("Guest") GUEST,
}
