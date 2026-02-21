package io.github.yvasyliev.deezer.feign;

import feign.Param;
import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.model.AccessToken;

public class AccessTokenExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        return value instanceof AccessToken accessToken ? expand(accessToken) : null;
    }

    private String expand(AccessToken accessToken) {
        if (accessToken.isExpired()) {
            throw new DeezerException("Access token is expired");
        }

        return accessToken.token();
    }
}
