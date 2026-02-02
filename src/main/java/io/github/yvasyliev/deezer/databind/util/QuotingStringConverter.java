package io.github.yvasyliev.deezer.databind.util;

import tools.jackson.databind.util.StdConverter;

public class QuotingStringConverter extends StdConverter<String, String> {
    private static final String QUOTE = "\"";

    @Override
    public String convert(String value) {
        return QUOTE + value + QUOTE;
    }
}
