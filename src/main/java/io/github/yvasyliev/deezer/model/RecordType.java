package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;

@Experimental
public enum RecordType {
    @JsonProperty("album") ALBUM,
    @JsonProperty("compilation") COMPILATION,
    @JsonProperty("ep") EP,
    @JsonProperty("single") SINGLE,
}
