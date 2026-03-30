package io.github.yvasyliev.dz4j.feign;

import io.github.yvasyliev.dz4j.model.AccessToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccessTokenExpanderTest {
    @Test
    void testExpand() {
        var expected = "token";
        var actual = new AccessTokenExpander().expand(new AccessToken(expected));

        assertEquals(expected, actual);
    }
}
