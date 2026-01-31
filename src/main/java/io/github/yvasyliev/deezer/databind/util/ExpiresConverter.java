package io.github.yvasyliev.deezer.databind.util;

import tools.jackson.databind.util.StdConverter;

import java.time.Duration;
import java.time.Instant;

public class ExpiresConverter extends StdConverter<Duration, Instant> {
    @Override
    public Instant convert(Duration duration) {
        return Instant.now().plus(duration);
    }
}
