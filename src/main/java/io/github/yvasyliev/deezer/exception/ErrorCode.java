package io.github.yvasyliev.deezer.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.yvasyliev.deezer.annotation.Experimental;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
    QUOTA(4),
    @Experimental APPLICATION_ERROR(50),
    ITEMS_LIMIT_EXCEEDED(100),
    PERMISSION(200),
    TOKEN_INVALID(300),
    PARAMETER(500),
    PARAMETER_MISSING(501),
    QUERY_INVALID(600),
    SERVICE_BUSY(700),
    DATA_NOT_FOUND(800),
    INDIVIDUAL_ACCOUNT_NOT_ALLOWED(901);

    @Getter(onMethod_ = @JsonValue)
    private final int code;
}
