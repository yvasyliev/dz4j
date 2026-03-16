package io.github.yvasyliev.deezer.databind.util;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.util.StdConverter;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpiresConverterTest {
    private static final Clock CLOCK = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    private static final StdConverter<Duration, Instant> CONVERTER = new ExpiresConverter(CLOCK);

    @Test
    void testConvert() {
        var duration = Duration.ofSeconds(10);
        var expected = Instant.now(CLOCK).plus(duration);

        var actual = CONVERTER.convert(duration);

        assertEquals(expected, actual);
    }
}
