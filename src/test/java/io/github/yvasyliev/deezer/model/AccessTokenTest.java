package io.github.yvasyliev.deezer.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccessTokenTest {
    @Test
    void shouldReturnTrueWhenExpiresAtIsNull() {
        var accessToken = new AccessToken("token", null);

        assertTrue(accessToken.isExpired());
    }

    @Test
    void shouldReturnTrueWhenTokenIsExpired() {
        var accessToken = new AccessToken("token", Instant.now().minusSeconds(3600));

        assertTrue(accessToken.isExpired());
    }

    @Test
    void shouldReturnFalseWhenTokenIsNotExpired() {
        var accessToken = new AccessToken("token", Instant.now().plusSeconds(3600));

        assertFalse(accessToken.isExpired());
    }
}
