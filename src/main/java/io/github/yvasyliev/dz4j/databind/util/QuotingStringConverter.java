package io.github.yvasyliev.dz4j.databind.util;

import tools.jackson.databind.util.StdConverter;

/**
 * A custom string converter that surrounds the input string with double quotes.
 */
public class QuotingStringConverter extends StdConverter<String, String> {
    private static final String QUOTE = "\"";

    /**
     * Converts a string by surrounding it with double quotes.
     *
     * @param value the string to convert
     * @return the converted string, which is the original string surrounded by double quotes
     */
    @Override
    public String convert(String value) {
        return QUOTE + value + QUOTE;
    }
}
