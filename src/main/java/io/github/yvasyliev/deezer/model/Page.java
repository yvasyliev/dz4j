package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;

import java.net.URL;
import java.util.List;

public record Page<T>(
        @JsonProperty("data") List<T> data,
        @JsonProperty("total") Integer total,
        @Experimental @JsonProperty("checksum") String checksum,
        @JsonProperty("next") URL next,
        @JsonProperty("prev") URL prev
) {}
