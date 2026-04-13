package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.dz4j.annotation.Experimental;

/**
 * Represents the result of a notification operation, indicating whether it was successful.
 *
 * @param success a boolean value indicating whether the notification operation was successful
 */
@Experimental
public record NotificationResult(@JsonProperty("success") Boolean success) {}
