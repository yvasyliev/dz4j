package io.github.yvasyliev.deezer.databind.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ZeroToNullLocalDateTimeConverterTest {
    private static final StdConverter<String, LocalDateTime> CONVERTER = new ZeroToNullLocalDateTimeConverter();

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> testConvert = () -> Stream.of(
            arguments("0000-00-00 00:00:00", null),
            arguments("2024-01-21 21:34:15", LocalDateTime.of(2024, 1, 21, 21, 34, 15))
    );

    @ParameterizedTest
    @FieldSource
    void testConvert(String input, LocalDateTime expected) {
        var actual = CONVERTER.convert(input);

        assertEquals(expected, actual);
    }
}
