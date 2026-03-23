package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Exception thrown when there is an error related to data processing in the Deezer API.
 */
public class DataException extends DeezerApiException {
    /**
     * Constructs a new {@link DataException} with the specified message and error code.
     *
     * @param message the detail message explaining the reason for the exception
     * @param code    the error code associated with the exception
     */
    public DataException(@JsonProperty(MESSAGE) String message, @JsonProperty(CODE) ErrorCode code) {
        super(message, code);
    }
}
