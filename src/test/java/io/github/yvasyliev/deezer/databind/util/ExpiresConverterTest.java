package io.github.yvasyliev.deezer.databind.util;

import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tools.jackson.databind.util.StdConverter;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

class ExpiresConverterTest {
    private static final StdConverter<Duration, Instant> EXPIRES_CONVERTER = new ExpiresConverter();

    @Test
    void testConvert() {
        var now = Instant.now();
        var duration = Duration.ofSeconds(10);
        var expected = now.plus(duration);
        @Cleanup var instant = mockStatic(Instant.class, Mockito.CALLS_REAL_METHODS);

        instant.when(Instant::now).thenReturn(now);

        var actual = EXPIRES_CONVERTER.convert(duration);

        assertEquals(expected, actual);
    }
}
