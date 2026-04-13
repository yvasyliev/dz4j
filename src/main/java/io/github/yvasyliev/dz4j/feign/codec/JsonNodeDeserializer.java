package io.github.yvasyliev.dz4j.feign.codec;

import io.github.yvasyliev.dz4j.exception.AbstractDeezerApiException;
import org.jspecify.annotations.Nullable;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;

import java.lang.reflect.Type;

/**
 * Functional interface for deserializing a {@link JsonNode} into a Java object of a specified type.
 */
@FunctionalInterface
public interface JsonNodeDeserializer {
    /**
     * Deserializes a {@link JsonNode} into a Java object of the specified type.
     *
     * @param body the JSON node to deserialize
     * @param type the type to deserialize into
     * @return the deserialized Java object
     * @throws JacksonException           if an error occurs during deserialization
     * @throws AbstractDeezerApiException if the API response indicates an error (e.g., contains an {@code error}
     *                                    field)
     */
    @Nullable
    Object deserialize(JsonNode body, Type type) throws JacksonException, AbstractDeezerApiException;
}
