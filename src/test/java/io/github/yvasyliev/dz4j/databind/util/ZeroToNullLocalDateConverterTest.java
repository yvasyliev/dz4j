package io.github.yvasyliev.dz4j.databind.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.databind.util.StdConverter;

class ZeroToNullLocalDateConverterTest {
    private static final StdConverter<String, LocalDate> CONVERTER = new ZeroToNullLocalDateConverter();

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> testConvert =
            () -> Stream.of(arguments("0000-00-00", null), arguments("2024-01-21", LocalDate.of(2024, 1, 21)));

    @ParameterizedTest
    @FieldSource
    void testConvert(String input, LocalDate expected) {
        var actual = CONVERTER.convert(input);

        assertEquals(expected, actual);
    }
}
