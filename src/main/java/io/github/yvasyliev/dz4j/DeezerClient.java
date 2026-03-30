package io.github.yvasyliev.dz4j;

import feign.AsyncFeign;
import io.github.yvasyliev.dz4j.authorization.Authorization;
import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.configuration.BaseUrls;
import io.github.yvasyliev.dz4j.factory.RequestFactoryProvider;
import io.github.yvasyliev.dz4j.feign.DeezerFeignBuilder;
import io.github.yvasyliev.dz4j.util.Customizer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

/**
 * The main client class for interacting with the Deezer API.
 *
 * <p>
 * It provides methods for authorization and access to various services. The client can be configured with custom base
 * URLs, Feign configurations, and access token suppliers. It also supports dynamic authorization by allowing the access
 * token to be updated at runtime.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeezerClient {
    @Delegate private final RequestFactoryProvider requestFactoryProvider;
    private final AuthorizationManager authorizationManager;

    //region constructors

    /**
     * Creates a new instance of {@link DeezerClient} without any authorization.
     */
    public DeezerClient() {
        this(null, null, (Authorization) null);
    }

    /**
     * Creates a new instance of {@link DeezerClient} with the provided {@link Authorization} method for authorization.
     *
     * @param authorization the {@link Authorization} method to be used for authorization
     */
    public DeezerClient(Authorization authorization) {
        this(null, null, authorization);
    }

    @Builder
    private DeezerClient(
            @Nullable Consumer<DeezerFeignBuilder> config,
            @Nullable Consumer<BaseUrls.BaseUrlsBuilder> baseUrl,
            @Nullable Authorization authorization
    ) {
        this(
                Customizer.customize(new DeezerFeignBuilder(), config).build(),
                Customizer.customize(BaseUrls.builder(), baseUrl).build(),
                new AuthorizationManager()
        );
        authorizationManager.setAuth(requestFactoryProvider.oauth());
        authorizationManager.setAuthorization(authorization);
    }

    private DeezerClient(
            AsyncFeign.AsyncBuilder<Object> feign,
            BaseUrls baseUrls,
            AuthorizationManager authorizationManager
    ) {
        this(new RequestFactoryProvider(feign, baseUrls, authorizationManager), authorizationManager);
    }

    //endregion

    /**
     * Sets the authorization for the client, allowing it to make authorized requests to the Deezer API.
     *
     * @param authorization the {@link Authorization} method to be used for authorization
     */
    public void authorization(Authorization authorization) {
        authorizationManager.setAuthorization(authorization);
    }

    /**
     * Removes the authorization from the client, effectively making it unauthorized.
     */
    public void removeAuthorization() {
        authorizationManager.setAuthorization(null);
    }
}
