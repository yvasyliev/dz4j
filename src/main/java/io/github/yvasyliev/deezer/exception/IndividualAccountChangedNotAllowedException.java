package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Exception thrown when an attempt is made to change an individual account, which is not allowed by the Deezer API.
 */
public class IndividualAccountChangedNotAllowedException extends DeezerApiException {
    /**
     * Constructs a new {@link IndividualAccountChangedNotAllowedException} with the specified message and error code.
     *
     * @param message the detail message explaining the reason for the exception
     * @param code    the error code associated with the exception
     */
    public IndividualAccountChangedNotAllowedException(
            @JsonProperty(MESSAGE) String message,
            @JsonProperty(CODE) ErrorCode code
    ) {
        super(message, code);
    }
}
