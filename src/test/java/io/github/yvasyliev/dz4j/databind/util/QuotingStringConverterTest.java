package io.github.yvasyliev.dz4j.databind.util;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.util.StdConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuotingStringConverterTest {
    private static final StdConverter<String, String> CONVERTER = new QuotingStringConverter();

    @Test
    void convert() {
        var input = "test";
        var expected = "\"test\"";

        var actual = CONVERTER.convert(input);

        assertEquals(expected, actual);
    }
}
