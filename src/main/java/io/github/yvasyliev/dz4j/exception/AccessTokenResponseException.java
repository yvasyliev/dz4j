package io.github.yvasyliev.dz4j.exception;

import feign.Response;

/**
 * Exception thrown when an error occurs while processing the access token response from Deezer API.
 */
public class AccessTokenResponseException extends ResponseException {
    /**
     * Constructs a new {@link AccessTokenResponseException} with the specified message and response.
     *
     * @param message  the detail message explaining the reason for the exception
     * @param response the response from the Deezer API that caused the exception
     */
    public AccessTokenResponseException(String message, Response response) {
        super(message, response);
    }
}
