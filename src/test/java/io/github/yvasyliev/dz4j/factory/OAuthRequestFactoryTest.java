package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.exception.DeezerException;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Permission;
import io.github.yvasyliev.dz4j.service.OAuthService;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OAuthRequestFactoryTest {
    @InjectMocks private OAuthRequestFactory oAuthRequestFactory;
    @Mock private OAuthService oAuthService;

    @Test
    void testGetAccessToken() {
        var appId = 123;
        var secret = "test-secret";
        var code = "test-code";
        var expected = new AccessToken("test-token", Instant.now().plusSeconds(3600));

        when(oAuthService.getAccessTokenAsync(appId, secret, code))
                .thenReturn(CompletableFuture.completedFuture(expected));

        var actual = oAuthRequestFactory.getAccessToken(appId, secret, code).execute();

        assertEquals(expected, actual);
    }

    @Test
    void testGetLoginUrl() throws DeezerException {
        var appId = 123;
        var redirectUri = URI.create("https://example.com/callback");
        var permissions = List.of(Permission.BASIC_ACCESS, Permission.EMAIL);

        var actual = oAuthRequestFactory.getLoginUrl(appId, redirectUri, permissions);

        assertThat(actual)
                .hasProtocol("https")
                .hasHost("connect.deezer.com")
                .hasNoPort()
                .hasPath("/oauth/auth.php")
                .hasParameter("app_id", String.valueOf(appId))
                .hasParameter("redirect_uri", redirectUri.toString())
                .hasParameter("perms", "basic_access,email");
    }

    @Test
    void shouldThrowDeezerExceptionWhenLoginUrlIsMalformed() throws MalformedURLException {
        var redirectUri = URI.create("https://example.com/callback");
        var permissions = List.of(Permission.BASIC_ACCESS);
        var loginUri = mock(URI.class);
        @Cleanup var uri = mockStatic(URI.class);

        uri.when(() -> URI.create(any())).thenReturn(loginUri);
        when(loginUri.toURL()).thenThrow(MalformedURLException.class);

        assertThrows(DeezerException.class, () -> oAuthRequestFactory.getLoginUrl(123, redirectUri, permissions));
    }
}
