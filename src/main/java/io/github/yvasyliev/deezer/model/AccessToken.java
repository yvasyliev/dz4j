package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.databind.util.ExpiresConverter;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;

public record AccessToken(
        @JsonProperty("access_token") String token,
        @JsonProperty("expires") @JsonDeserialize(converter = ExpiresConverter.class) Instant expiresAt
) {
    //TODO: fix + test
    public boolean isExpired() {
        return expiresAt != null && Instant.now().isAfter(expiresAt);
    }
}
