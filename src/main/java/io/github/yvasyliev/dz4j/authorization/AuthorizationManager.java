package io.github.yvasyliev.dz4j.authorization;

import io.github.yvasyliev.dz4j.factory.OAuthRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Manages the authorization state and provides access tokens for API requests.
 */
@Setter
public class AuthorizationManager {
    private static final CompletableFuture<AccessToken> DEFAULT_TOKEN = CompletableFuture.completedFuture(null);

    @Nullable
    private volatile Authorization authorization;

    @SuppressWarnings("NullAway.Init")
    private OAuthRequestFactory auth;

    /**
     * Retrieves the current access token for API requests. If the authorization is not set, it returns a completed
     * future with {@code null}.
     *
     * @return a {@link CompletableFuture} that will complete with the access token, or {@code null} if not authorized
     */
    public CompletableFuture<AccessToken> getToken() {
        var localAuthorization = authorization;

        return localAuthorization != null ? localAuthorization.getAccessToken(auth) : DEFAULT_TOKEN;
    }
}
