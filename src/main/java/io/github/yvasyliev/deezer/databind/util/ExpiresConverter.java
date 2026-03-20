package io.github.yvasyliev.deezer.databind.util;

import lombok.Builder;
import tools.jackson.databind.util.StdConverter;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Builder
public class ExpiresConverter extends StdConverter<Duration, Instant> {
    @Builder.Default
    private final Clock clock = Clock.systemDefaultZone();

    @Override
    public Instant convert(Duration duration) {
        return Duration.ZERO.equals(duration) ? Instant.MAX : Instant.now(clock).plus(duration);
    }
}
