package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;
import lombok.Builder;
import lombok.Singular;

import java.net.URL;
import java.util.List;

@Builder
public record Page<T>(
        @JsonProperty("data") @Singular("data") List<T> data,
        @JsonProperty("total") Integer total,
        @Experimental @JsonProperty("checksum") String checksum,
        @JsonProperty("next") URL next,
        @JsonProperty("prev") URL prev
) {}
