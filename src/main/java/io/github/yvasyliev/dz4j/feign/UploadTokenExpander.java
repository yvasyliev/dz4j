package io.github.yvasyliev.dz4j.feign;

import feign.Param;
import io.github.yvasyliev.dz4j.model.Infos;

/**
 * Expander for extracting the upload token from an {@link Infos} object.
 */
public class UploadTokenExpander implements Param.Expander {
    /**
     * Expands an {@link Infos} object to extract the upload token for use in Feign requests.
     *
     * @param value the value to expand, expected to be an instance of {@link Infos}
     * @return the upload token extracted from the {@link Infos} object
     */
    @Override
    public String expand(Object value) {
        return ((Infos) value).uploadToken();
    }
}
