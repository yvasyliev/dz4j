package io.github.yvasyliev.deezer.util;

import io.github.yvasyliev.deezer.model.AccessToken;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthHelperTest {
    @Test
    void testCreateAccessToken() {
        var token = "test-token";
        var expected = new AccessToken(token, Instant.MAX);
        var actual = AuthHelper.createAccessToken(token);

        assertEquals(expected, actual);
    }

    @Test
    void testCreateAccessTokenFuture() {
        var expected = new AccessToken("test-token", Instant.MAX);
        var actual = AuthHelper.createAccessTokenFuture(expected).join();

        assertEquals(expected, actual);
    }
}
