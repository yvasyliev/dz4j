package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AuthorizationTest {
    @Test
    void testGetAccessToken() {
        var auth = mock(OAuthRequestFactory.class);
        var expected = new AccessToken("token");
        var authorization = Authorization.of(expected);
        var actual = authorization.getAccessToken(auth);

        assertThat(actual).isCompletedWithValue(expected);

        actual = authorization.getAccessToken(auth);

        assertThat(actual).isCompletedWithValue(expected);
    }
}
