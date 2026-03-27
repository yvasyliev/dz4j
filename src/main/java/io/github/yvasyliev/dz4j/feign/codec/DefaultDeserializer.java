package io.github.yvasyliev.dz4j.feign.codec;

import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.lang.reflect.Type;

/**
 * Default implementation of {@link JsonNodeDeserializer} that uses {@link JsonMapper} to deserialize JSON nodes.
 */
@RequiredArgsConstructor
public class DefaultDeserializer implements JsonNodeDeserializer {
    private final JsonMapper jsonMapper;

    /**
     * {@inheritDoc}
     *
     * @param body {@inheritDoc}
     * @param type {@inheritDoc}
     * @return {@inheritDoc}
     * @throws JacksonException {@inheritDoc}
     */
    @Override
    public Object deserialize(JsonNode body, Type type) throws JacksonException {
        return jsonMapper.treeToValue(body, jsonMapper.constructType(type));
    }
}
