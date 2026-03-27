package io.github.yvasyliev.dz4j.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.yvasyliev.dz4j.annotation.Experimental;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Error codes returned by the Deezer API.
 */
@RequiredArgsConstructor
public enum ErrorCode {
    /**
     * Quota exceeded.
     */
    QUOTA(4),

    /**
     * Application error.
     */
    @Experimental
    APPLICATION_ERROR(50),

    /**
     * Items limit exceeded.
     */
    ITEMS_LIMIT_EXCEEDED(100),

    /**
     * Permission denied.
     */
    PERMISSION(200),

    /**
     * Invalid token.
     */
    TOKEN_INVALID(300),

    /**
     * Not found.
     */
    @Experimental
    NOT_FOUND(404),

    /**
     * Invalid parameter.
     */
    PARAMETER(500),

    /**
     * Missing parameter.
     */
    PARAMETER_MISSING(501),

    /**
     * Invalid query.
     */
    QUERY_INVALID(600),

    /**
     * Service is busy.
     */
    SERVICE_BUSY(700),

    /**
     * Data not found.
     */
    DATA_NOT_FOUND(800),

    /**
     * Individual account is not allowed.
     */
    INDIVIDUAL_ACCOUNT_NOT_ALLOWED(901);

    @Getter(onMethod_ = @JsonValue)
    private final int code;
}
