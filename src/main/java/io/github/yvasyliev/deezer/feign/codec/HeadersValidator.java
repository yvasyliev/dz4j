package io.github.yvasyliev.deezer.feign.codec;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;

import java.lang.reflect.Type;

/**
 * A response validator that checks for the presence of the {@code Content-Type} header in the response.
 */
public class HeadersValidator implements ResponseValidator {
    /**
     * Validates the response by checking for the presence of the {@code Content-Type} header.
     *
     * @param response {@inheritDoc}
     * @param type     {@inheritDoc}
     * @throws ResponseException if the {@code Content-Type} header is not present in the response
     */
    @Override
    public void validate(Response response, Type type) throws ResponseException {
        if (!response.headers().containsKey("content-type")) {
            throw new ResponseException("No Content-Type response header present", response);
        }
    }
}
