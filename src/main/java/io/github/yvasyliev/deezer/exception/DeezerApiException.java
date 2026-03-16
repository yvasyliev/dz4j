package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.ToString;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeezerApiResponseException.class, name = "Exception"),
        @JsonSubTypes.Type(value = OAuthException.class, name = "OAuthException"),
        @JsonSubTypes.Type(value = ParameterException.class, name = "ParameterException"),
        @JsonSubTypes.Type(value = MissingParameterException.class, name = "MissingParameterException"),
        @JsonSubTypes.Type(value = InvalidQueryException.class, name = "InvalidQueryException"),
        @JsonSubTypes.Type(value = DataException.class, name = "DataException"),
        @JsonSubTypes.Type(
                value = IndividualAccountChangedNotAllowedException.class,
                name = "IndividualAccountChangedNotAllowedException"
        ),
        @JsonSubTypes.Type(value = SimpleApiHttpException.class, name = "SimpleApiHttpException")
})
@Getter
@ToString(callSuper = true)
public abstract class DeezerApiException extends DeezerException {
    protected static final String MESSAGE = "message";
    protected static final String CODE = "code";

    private final ErrorCode code;

    public DeezerApiException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
}
