package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExplicitContent {
    NOT_EXPLICIT(0),
    EXPLICIT(1),
    UNKNOWN(2),
    EDITED(3),
    NO_ADVICE_AVAILABLE(6),
    PARTIALLY_NO_ADVICE_AVAILABLE(7);

    @Getter(onMethod_ = @JsonValue)
    private final int code;
}
