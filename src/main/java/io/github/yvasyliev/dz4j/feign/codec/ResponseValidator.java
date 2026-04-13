package io.github.yvasyliev.dz4j.feign.codec;

import feign.Response;
import io.github.yvasyliev.dz4j.exception.ResponseException;

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
