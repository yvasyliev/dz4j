package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;

@Experimental
public class SimpleApiHttpException extends DeezerApiException {
    public SimpleApiHttpException(@JsonProperty(MESSAGE) String message, @JsonProperty(CODE) ErrorCode code) {
        super(message, code);
    }
}
