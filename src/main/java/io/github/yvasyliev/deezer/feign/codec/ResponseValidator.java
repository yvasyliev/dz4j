package io.github.yvasyliev.deezer.feign.codec;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;

import java.lang.reflect.Type;

/**
 * Functional interface for validating a Deezer API response.
 */
@FunctionalInterface
public interface ResponseValidator {
    /**
     * Validates the given response against the expected type.
     *
     * @param response the response to validate
     * @param type     the expected type of the response body
     * @throws ResponseException if the response is invalid or cannot be deserialized into the expected type
     */
    void validate(Response response, Type type) throws ResponseException;
}
