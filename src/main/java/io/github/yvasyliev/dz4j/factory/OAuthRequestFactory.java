package io.github.yvasyliev.dz4j.factory;

import feign.CollectionFormat;
import feign.RequestTemplate;
import io.github.yvasyliev.dz4j.exception.DeezerException;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Permission;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.request.SimpleDeezerRequest;
import io.github.yvasyliev.dz4j.service.OAuthService;
import lombok.RequiredArgsConstructor;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

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
    public URL getLoginUrl(int appId, URI redirectUri, Collection<Permission> permissions)
            throws DeezerException {
        var url = new RequestTemplate()
                .target("https://connect.deezer.com")
                .uri("/oauth/auth.php?app_id={app-id}&redirect_uri={redirect-uri}&perms={perms}")
                .collectionFormat(CollectionFormat.CSV)
                .resolve(Map.of(
                        "app-id", appId,
                        "redirect-uri", redirectUri,
                        "perms", permissions.stream().map(Permission::getValue).toList()
                ))
                .url();

        try {
            return URI.create(url).toURL();
        } catch (MalformedURLException e) {
            throw new DeezerException("Failed to generate login URL", e);
        }
    }
}
