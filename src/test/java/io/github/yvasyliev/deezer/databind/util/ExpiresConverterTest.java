package io.github.yvasyliev.deezer.databind.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.databind.util.StdConverter;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ExpiresConverterTest {
    private static final Clock CLOCK = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    private static final StdConverter<Duration, Instant> CONVERTER = new ExpiresConverter(CLOCK);

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> testConvert = () -> {
        var duration = Duration.ofSeconds(10);

        return Stream.of(
                arguments(duration, Instant.now(CLOCK).plus(duration)),
                arguments(Duration.ZERO, Instant.MAX)
        );
    };

    @ParameterizedTest
    @FieldSource
    void testConvert(Duration duration, Instant expected) {
        var actual = CONVERTER.convert(duration);

        assertEquals(expected, actual);
    }
}
