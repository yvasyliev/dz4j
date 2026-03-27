package io.github.yvasyliev.dz4j.util;

import io.github.yvasyliev.dz4j.authorization.TokenManager;
import io.github.yvasyliev.dz4j.factory.InfosRequestFactory;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Infos;
import lombok.experimental.UtilityClass;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Utility class for creating token managers for access tokens and upload tokens.
 */
@UtilityClass
public class TokenManagers {
    /**
     * Creates a token manager for access tokens that always considers the token valid and retrieves it using the
     * provided supplier.
     *
     * @param accessTokenSupplier a supplier that retrieves the access token as a {@link CompletableFuture}
     * @return a token manager for access tokens
     */
    public TokenManager<AccessToken> accessTokenManager(
            Supplier<CompletableFuture<AccessToken>> accessTokenSupplier
    ) {
        return new TokenManager<>(token -> true, accessTokenSupplier, AccessToken::token);
    }

    /**
     * Creates a token manager for upload tokens that considers the token valid if it is not done or if it is completed
     * without an exception and the upload token is not expired. The token is retrieved using the provided
     * {@link InfosRequestFactory}.
     *
     * @param infos a factory to create requests for retrieving the upload token
     * @return a token manager for upload tokens
     */
    public TokenManager<Infos> uploadTokenManager(InfosRequestFactory infos) {
        return new TokenManager<>(
                token -> !token.isDone() || !token.isCompletedExceptionally() && !token.join().isUploadTokenExpired(),
                () -> infos.getInfos().executeAsync(),
                Infos::uploadToken
        );
    }
}
