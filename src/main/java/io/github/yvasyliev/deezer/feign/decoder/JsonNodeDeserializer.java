package io.github.yvasyliev.deezer.feign.decoder;

import io.github.yvasyliev.deezer.exception.DeezerApiException;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;

import java.lang.reflect.Type;

@FunctionalInterface
public interface JsonNodeDeserializer {
    <T> T deserialize(JsonNode body, Type type) throws JacksonException, DeezerApiException;
}
