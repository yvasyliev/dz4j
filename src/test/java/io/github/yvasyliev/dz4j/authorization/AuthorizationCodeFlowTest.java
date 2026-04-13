package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthorizationCodeFlowTest {
    @Test
    void testGetAccessToken() {
        var appId = 123;
        var secret = "secret";
        var code = "code";
        var auth = mock(OAuthRequestFactory.class);
        var expected = new AccessToken("token");
        var request = Mockito.<DeezerRequest<AccessToken>>mock();
        var authorization = new AuthorizationCodeFlow(appId, secret, code);

        when(auth.getAccessToken(appId, secret, code)).thenReturn(request);
        when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = authorization.getAccessToken(auth);

        assertThat(actual).isCompletedWithValue(expected);

        actual = authorization.getAccessToken(auth);

        assertThat(actual).isCompletedWithValue(expected);

        verify(auth, times(1)).getAccessToken(appId, secret, code);
    }
}
