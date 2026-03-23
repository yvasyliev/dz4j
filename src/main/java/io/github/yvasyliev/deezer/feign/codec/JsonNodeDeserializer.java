package io.github.yvasyliev.deezer.feign.codec;

import io.github.yvasyliev.deezer.exception.AbstractDeezerApiException;
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
     * @param <T>  the type of the resulting Java object
     * @return the deserialized Java object
     * @throws JacksonException           if an error occurs during deserialization
     * @throws AbstractDeezerApiException if the API response indicates an error (e.g., contains an {@code error}
     *                                    field)
     */
    <T> T deserialize(JsonNode body, Type type) throws JacksonException, AbstractDeezerApiException;
}
