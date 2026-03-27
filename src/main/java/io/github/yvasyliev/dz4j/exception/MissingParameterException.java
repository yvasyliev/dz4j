package io.github.yvasyliev.dz4j.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Exception thrown when a required parameter is missing in a request to the Deezer API.
 */
public class MissingParameterException extends AbstractDeezerApiException {
    /**
     * Constructs a new {@link MissingParameterException} with the specified message and error code.
     *
     * @param message the detail message explaining the reason for the exception
     * @param code    the error code associated with the missing parameter
     */
    public MissingParameterException(@JsonProperty(MESSAGE) String message, @JsonProperty(CODE) ErrorCode code) {
        super(message, code);
    }
}
