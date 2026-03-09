package io.github.yvasyliev.deezer.databind.util;

import tools.jackson.databind.util.StdConverter;

import java.time.LocalDate;

public class ZeroToNullLocalDateConverter extends StdConverter<String, LocalDate> {
    @Override
    public LocalDate convert(String value) {
        return "0000-00-00".equals(value) ? null : LocalDate.parse(value);
    }
}
