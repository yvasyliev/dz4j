package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Exception thrown when a query to the Deezer API is invalid.
 */
public class InvalidQueryException extends AbstractDeezerApiException {
    /**
     * Constructs a new {@link InvalidQueryException} with the specified message and error code.
     *
     * @param message the detail message explaining the reason for the exception
     * @param code    the error code associated with the exception
     */
    public InvalidQueryException(@JsonProperty(MESSAGE) String message, @JsonProperty(CODE) ErrorCode code) {
        super(message, code);
    }
}
