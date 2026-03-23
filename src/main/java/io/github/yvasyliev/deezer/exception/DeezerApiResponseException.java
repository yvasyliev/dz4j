package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Exception thrown when Deezer API returns an error response.
 */
public class DeezerApiResponseException extends AbstractDeezerApiException {
    /**
     * Constructs a new {@link DeezerApiResponseException} with the specified message and error code.
     *
     * @param message the detail message explaining the reason for the exception
     * @param code    the error code returned by the Deezer API
     */
    public DeezerApiResponseException(@JsonProperty(MESSAGE) String message, @JsonProperty(CODE) ErrorCode code) {
        super(message, code);
    }
}
