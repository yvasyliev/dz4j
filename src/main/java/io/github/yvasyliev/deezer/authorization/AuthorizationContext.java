package io.github.yvasyliev.deezer.authorization;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorizationContext {
    private AccessTokenProvider accessTokenProvider = () -> null;
}
