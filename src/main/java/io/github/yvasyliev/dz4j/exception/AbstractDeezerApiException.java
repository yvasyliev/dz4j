package io.github.yvasyliev.dz4j.exception;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.ToString;

/**
 * Base class for all exceptions thrown by the Deezer API. It is used to deserialize error responses from the API.
 */
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
public abstract class AbstractDeezerApiException extends DeezerException {
    /**
     * A message field.
     */
    protected static final String MESSAGE = "message";

    /**
     * A code field.
     */
    protected static final String CODE = "code";

    private final ErrorCode code;

    /**
     * Constructs a new {@code DeezerApiException} with the specified message and error code.
     *
     * @param message the detail message explaining the reason for the exception
     * @param code    the error code associated with the exception
     */
    protected AbstractDeezerApiException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
}
