package io.github.yvasyliev.deezer.databind.util;

import org.jspecify.annotations.Nullable;
import tools.jackson.databind.util.StdConverter;

import java.time.LocalDate;

/**
 * A converter that converts a string to a {@link LocalDate}, returning {@code null} if the string is
 * {@code 0000-00-00}.
 */
public class ZeroToNullLocalDateConverter extends StdConverter<String, LocalDate> {
    /**
     * Converts a string to a {@link LocalDate}, returning {@code null} if the string is {@code 0000-00-00}.
     *
     * @param value the string to convert
     * @return the resulting {@link LocalDate}, or {@code null} if the input string is {@code 0000-00-00}
     */
    @Override
    @Nullable
    public LocalDate convert(String value) {
        return "0000-00-00".equals(value) ? null : LocalDate.parse(value);
    }
}
