package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Permission;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.request.SimpleDeezerRequest;
import io.github.yvasyliev.deezer.service.OAuthService;
import lombok.RequiredArgsConstructor;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Factory for creating OAuth-related requests.
 */
@RequiredArgsConstructor
public class OAuthRequestFactory {
    private final OAuthService oauthService;

    /**
     * Creates a request to exchange an authorization code for an access token.
     *
     * @param appId  the application ID
     * @param secret the application secret
     * @param code   the authorization code received from the OAuth flow
     * @return request to exchange an authorization code for an access token
     */
    public DeezerRequest<AccessToken> getAccessToken(int appId, String secret, String code) {
        return new SimpleDeezerRequest<>(() -> oauthService.getAccessTokenAsync(appId, secret, code));
    }

    /**
     * Generates the login URL for the OAuth flow.
     *
     * @param appId       the application ID
     * @param redirectUri the URI to redirect to after login
     * @param permissions the permissions to request
     * @return the login URL
     * @throws DeezerException if URL generation fails
     */
    public URL getLoginUrl(int appId, String redirectUri, Collection<Permission> permissions)
            throws DeezerException {
        var perms = permissions.stream().map(Permission::getValue).collect(Collectors.joining(","));
        var loginUrl = "https://connect.deezer.com/oauth/auth.php?app_id=%d&redirect_uri=%s&perms=%s".formatted(
                appId,
                URLEncoder.encode(redirectUri, StandardCharsets.UTF_8),
                URLEncoder.encode(perms, StandardCharsets.UTF_8)
        );

        try {
            return URI.create(loginUrl).toURL();
        } catch (MalformedURLException e) {
            throw new DeezerException("Failed to generate login URL", e);
        }
    }
}
