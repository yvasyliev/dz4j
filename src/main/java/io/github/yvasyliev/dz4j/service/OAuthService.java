package io.github.yvasyliev.dz4j.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.dz4j.model.AccessToken;

import java.util.concurrent.CompletableFuture;

/**
 * Service for handling OAuth authentication with Deezer.
 */
@Headers("Accept: application/json")
public interface OAuthService {
    /**
     * Retrieves an access token from Deezer using the provided application ID, secret, and authorization code.
     *
     * @param appId  the application ID for OAuth authentication
     * @param secret the application secret for OAuth authentication
     * @param code   the authorization code obtained from the OAuth flow
     * @return a {@link CompletableFuture} that will complete with the retrieved access token
     */
    @RequestLine("GET /oauth/access_token.php?app_id={appId}&code={code}&secret={secret}&output=json")
    CompletableFuture<AccessToken> getAccessTokenAsync(
            @Param("appId") int appId,
            @Param("secret") String secret,
            @Param("code") String code
    );
}
