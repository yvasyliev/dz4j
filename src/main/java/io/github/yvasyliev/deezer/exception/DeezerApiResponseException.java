package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeezerApiResponseException extends DeezerApiException {
    public DeezerApiResponseException(@JsonProperty(MESSAGE) String message, @JsonProperty(CODE) ErrorCode code) {
        super(message, code);
    }
}
