package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The explicit content values.
 */
@RequiredArgsConstructor
public enum ExplicitContent {
    /**
     * Not Explicit.
     */
    NOT_EXPLICIT(0),

    /**
     * Explicit.
     */
    EXPLICIT(1),

    /**
     * Unknown.
     */
    UNKNOWN(2),

    /**
     * Edited.
     */
    EDITED(3),

    /**
     * Partially Explicit (Album "lyrics" only).
     */
    PARTIALLY_EXPLICIT(4),

    /**
     * Partially Unknown (Album "lyrics" only).
     */
    PARTIALLY_UNKNOWN(5),

    /**
     * No Advice Available.
     */
    NO_ADVICE_AVAILABLE(6),

    /**
     * Partially No Advice Available (Album "lyrics" only).
     */
    PARTIALLY_NO_ADVICE_AVAILABLE(7);

    @Getter(onMethod_ = @JsonValue)
    private final int code;
}
