package io.github.yvasyliev.dz4j.databind.util;

import org.jspecify.annotations.Nullable;
import tools.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A converter that converts a string to a {@link LocalDateTime}, returning {@code null} if the string is
 * {@code 0000-00-00 00:00:00}.
 */
public class ZeroToNullLocalDateTimeConverter extends StdConverter<String, LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Converts a string to a {@link LocalDateTime}, returning {@code null} if the string is
     * {@code 0000-00-00 00:00:00}.
     *
     * @param value the string to convert
     * @return the resulting {@link LocalDateTime}, or {@code null} if the input string is {@code 0000-00-00 00:00:00}
     */
    @Override
    @Nullable
    public LocalDateTime convert(String value) {
        return "0000-00-00 00:00:00".equals(value) ? null : LocalDateTime.parse(value, FORMATTER);
    }
}
