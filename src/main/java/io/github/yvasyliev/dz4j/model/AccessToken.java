package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.dz4j.databind.util.ExpiresConverter;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;

/**
 * An access token for the Deezer API, including the token string and its expiration time.
 *
 * @param token     the access token string
 * @param expiresAt the expiration time of the access token as an {@link Instant}
 */
public record AccessToken(
        @JsonProperty("access_token") String token,
        @JsonProperty("expires") @JsonDeserialize(converter = ExpiresConverter.class) Instant expiresAt
) {
    /**
     * Constructs an {@code AccessToken} with a {@code null} token and an expiration time set to {@link Instant#MAX}.
     */
    public AccessToken() {
        this(null);
    }

    /**
     * Constructs an {@code AccessToken} with the specified token and an expiration time set to {@link Instant#MAX}.
     *
     * @param token the access token string
     */
    public AccessToken(String token) {
        this(token, Instant.MAX);
    }

    /**
     * Checks if the access token has expired by comparing the current time with the expiration time.
     *
     * @return {@code true} if the access token has expired, {@code false} otherwise
     */
    public boolean isExpired() {
        return expiresAt == null || Instant.now().isAfter(expiresAt);
    }
}
