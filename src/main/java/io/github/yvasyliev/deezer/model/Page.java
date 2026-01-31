package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.util.List;

public record Page<T>(
        @JsonProperty("data") List<T> data,
        @JsonProperty("total") Integer total,
        @JsonProperty("next") URL next,
        @JsonProperty("prev") URL prev
) {}
