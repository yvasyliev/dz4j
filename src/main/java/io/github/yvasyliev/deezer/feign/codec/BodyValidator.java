package io.github.yvasyliev.deezer.feign.codec;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;

import java.lang.reflect.Type;

/**
 * Validates the response body of a Deezer API response.
 */
public class BodyValidator implements ResponseValidator {
    /**
     * Validates the response body of a Deezer API response. If the body is empty, it throws a
     * {@link ResponseException}.
     *
     * @param response {@inheritDoc}
     * @param type     {@inheritDoc}
     * @throws ResponseException if the response body is empty
     */
    @Override
    public void validate(Response response, Type type) throws ResponseException {
        if (response.body() == null) {
            throw new ResponseException("Body is empty", response);
        }
    }
}
