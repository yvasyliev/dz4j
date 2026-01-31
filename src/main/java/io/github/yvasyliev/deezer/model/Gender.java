package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("F") FEMALE,
    @JsonProperty("M") MALE,
}
