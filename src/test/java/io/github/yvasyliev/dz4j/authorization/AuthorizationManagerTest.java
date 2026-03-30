package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorizationManagerTest {
    @Test
    void shouldReturnNullWhenAuthorizationIsNotSet() {
        var actual = new AuthorizationManager().getToken();

        assertThat(actual).isCompletedWithValue(null);
    }

    @Test
    void shouldReturnTokenWhenAuthorizationIsSet() {
        var authorization = mock(Authorization.class);
        var auth = mock(OAuthRequestFactory.class);
        var expected = new AccessToken("token");
        var authorizationManager = new AuthorizationManager();

        authorizationManager.setAuthorization(authorization);
        authorizationManager.setAuth(auth);

        when(authorization.getAccessToken(auth)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = authorizationManager.getToken();

        assertThat(actual).isCompletedWithValue(expected);
    }
}
