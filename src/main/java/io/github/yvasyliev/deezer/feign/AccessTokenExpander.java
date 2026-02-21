package io.github.yvasyliev.deezer.feign;

import feign.Param;
import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.model.AccessToken;

public class AccessTokenExpander implements Param.Expander {
    private static final String MESSAGE_TEMPLATE = "%s expects %s type, but got: %%s".formatted(
            AccessTokenExpander.class.getSimpleName(),
            AccessToken.class.getName()
    );

    @Override
    public String expand(Object value) {
        if (value instanceof AccessToken accessToken) {
            if (accessToken.isExpired()) {
                throw new DeezerException("Access token is expired");
            }

            return accessToken.token();
        }

        throw new DeezerException(MESSAGE_TEMPLATE.formatted(value));
    }
}
