package io.github.yvasyliev.dz4j.feign;

import feign.Param;
import io.github.yvasyliev.dz4j.model.AccessToken;

/**
 * An expander for {@link AccessToken} objects that extracts the token string for use in Feign requests.
 */
public class AccessTokenExpander implements Param.Expander {
    /**
     * Expands an {@link AccessToken} object to its token string representation.
     *
     * @param value the value to expand, expected to be an instance of {@link AccessToken}
     * @return the token string extracted from the {@link AccessToken} object
     */
    @Override
    public String expand(Object value) {
        return ((AccessToken) value).token();
    }
}
