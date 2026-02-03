package io.github.yvasyliev.deezer.request.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.databind.util.QuotingStringConverter;
import lombok.Builder;
import tools.jackson.databind.annotation.JsonSerialize;

import java.time.Duration;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AdvancedQuery(
        @JsonProperty("artist") @JsonSerialize(converter = QuotingStringConverter.class) String artist,
        @JsonProperty("album") @JsonSerialize(converter = QuotingStringConverter.class) String album,
        @JsonProperty("track") @JsonSerialize(converter = QuotingStringConverter.class) String track,
        @JsonProperty("label") @JsonSerialize(converter = QuotingStringConverter.class) String label,
        @JsonProperty("dur_min") @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT, pattern = "SECONDS") Duration durMin,
        @JsonProperty("dur_max") @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT, pattern = "SECONDS") Duration durMax,
        @JsonProperty("bpm_min") Integer bpmMin,
        @JsonProperty("bpm_max") Integer bpmMax
) implements Query {}
