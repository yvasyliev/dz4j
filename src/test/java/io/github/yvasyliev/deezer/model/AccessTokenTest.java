package io.github.yvasyliev.deezer.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccessTokenTest {
    @Test
    void shouldCreateStaticNullToken() {
        var expected = new AccessToken(null, Instant.MAX);
        var actual = new AccessToken();

        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateStaticToken() {
        var token = "token";
        var expected = new AccessToken(token, Instant.MAX);
        var actual = new AccessToken(token);

        assertEquals(expected, actual);
    }

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
