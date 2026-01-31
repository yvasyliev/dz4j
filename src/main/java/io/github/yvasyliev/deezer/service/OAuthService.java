package io.github.yvasyliev.deezer.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.util.concurrent.CompletableFuture;

@Headers("Accept: application/json")
public interface OAuthService {
    @RequestLine("GET /oauth/access_token.php?app_id={appId}&code={code}&secret={secret}&output=json")
    CompletableFuture<AccessToken> getAccessTokenAsync(
            @Param("appId") int appId,
            @Param("secret") String secret,
            @Param("code") String code
    );
}
