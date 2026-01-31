package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndividualAccountChangedNotAllowedException extends DeezerApiException {
    public IndividualAccountChangedNotAllowedException(
            @JsonProperty(MESSAGE) String message,
            @JsonProperty(CODE) ErrorCode code
    ) {
        super(message, code);
    }
}
