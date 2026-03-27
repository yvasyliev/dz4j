package io.github.yvasyliev.dz4j.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.dz4j.annotation.Experimental;

/**
 * Exception thrown when there is a simple HTTP error related to the Deezer API.
 */
@Experimental
public class SimpleApiHttpException extends AbstractDeezerApiException {
    /**
     * Constructs a new {@link SimpleApiHttpException} with the specified message and error code.
     *
     * @param message the detail message explaining the reason for the exception
     * @param code    the error code associated with the exception
     */
    public SimpleApiHttpException(@JsonProperty(MESSAGE) String message, @JsonProperty(CODE) ErrorCode code) {
        super(message, code);
    }
}
