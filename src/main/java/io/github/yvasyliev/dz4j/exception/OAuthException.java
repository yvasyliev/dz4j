package io.github.yvasyliev.dz4j.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Exception thrown when there is an error during the OAuth authentication process with the Deezer API.
 */
public class OAuthException extends AbstractDeezerApiException {
    /**
     * Constructs a new {@link OAuthException} with the specified message and error code.
     *
     * @param message the detail message explaining the reason for the exception
     * @param code    the error code associated with the exception
     */
    public OAuthException(@JsonProperty(MESSAGE) String message, @JsonProperty(CODE) ErrorCode code) {
        super(message, code);
    }
}
