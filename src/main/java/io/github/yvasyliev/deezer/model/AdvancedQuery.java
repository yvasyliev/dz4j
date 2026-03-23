package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.databind.util.QuotingStringConverter;
import lombok.Builder;
import tools.jackson.databind.annotation.JsonSerialize;

import java.time.Duration;

/**
 * An advanced query for searching operation on Deezer. All fields are optional and will be ignored if they are
 * {@code null}.
 *
 * @param artist an artist name to search for
 * @param album  an album name to search for
 * @param track  a track name to search for
 * @param label  a label name to search for
 * @param durMin a minimum track duration to search for
 * @param durMax a maximum track duration to search for
 * @param bpmMin a minimum track BPM to search for
 * @param bpmMax a maximum track BPM to search for
 */
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
