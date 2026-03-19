package io.github.yvasyliev.deezer.util;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.factory.InfosRequestFactory;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import lombok.experimental.UtilityClass;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@UtilityClass
public class TokenManagers {
    public TokenManager<AccessToken> accessTokenTokenManager(
            Supplier<CompletableFuture<AccessToken>> accessTokenSupplier
    ) {
        return new TokenManager<>(token -> true, accessTokenSupplier, AccessToken::token);
    }

    public TokenManager<Infos> uploadTokenManager(InfosRequestFactory infos) {
        return new TokenManager<>(
                token -> !token.isDone() || !token.isCompletedExceptionally() && !token.join().isUploadTokenExpired(),
                () -> infos.getInfos().executeAsync(),
                Infos::uploadToken
        );
    }
}
