package io.github.yvasyliev.deezer.databind.util;

import lombok.Builder;
import tools.jackson.databind.util.StdConverter;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

/**
 * A converter that transforms a {@link Duration} into an {@link Instant} by adding the duration to the current time.
 */
@Builder
public class ExpiresConverter extends StdConverter<Duration, Instant> {
    @Builder.Default
    private final Clock clock = Clock.systemDefaultZone();

    /**
     * Converts a {@link Duration} to an {@link Instant} by adding the duration to the current time.
     *
     * @param duration the duration to convert
     * @return the resulting instant, or {@link Instant#MAX} if the duration is zero
     */
    @Override
    public Instant convert(Duration duration) {
        return Duration.ZERO.equals(duration) ? Instant.MAX : Instant.now(clock).plus(duration);
    }
}
