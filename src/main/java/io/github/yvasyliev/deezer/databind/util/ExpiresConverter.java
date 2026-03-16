package io.github.yvasyliev.deezer.databind.util;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.util.StdConverter;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
public class ExpiresConverter extends StdConverter<Duration, Instant> {
    private final Clock clock;

    @Override
    public Instant convert(Duration duration) {
        return Instant.now(clock).plus(duration);
    }
}
