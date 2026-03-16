package io.github.yvasyliev.deezer.databind.util;

import tools.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZeroToNullLocalDateTimeConverter extends StdConverter<String, LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime convert(String value) {
        return "0000-00-00 00:00:00".equals(value) ? null : LocalDateTime.parse(value, FORMATTER);
    }
}
