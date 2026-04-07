package io.github.yvasyliev.dz4j.exception;

import feign.Response;
import lombok.Getter;

/**
 * Exception thrown when a response from the Deezer API is not eligible for JSON deserialization.
 */
@Getter
public class ResponseException extends DeezerException {
    private transient final Response response;

    /**
     * Constructs a new {@link ResponseException} with the specified message and response.
     *
     * @param message  the detail message explaining the reason for the exception
     * @param response the response from the Deezer API that caused the exception
     */
    public ResponseException(String message, Response response) {
        super(message);
        this.response = response;
    }
}
