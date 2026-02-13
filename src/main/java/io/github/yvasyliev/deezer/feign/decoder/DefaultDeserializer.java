package io.github.yvasyliev.deezer.feign.decoder;

import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class DefaultDeserializer implements JsonNodeDeserializer {
    private final JsonMapper jsonMapper;

    @Override
    public Object deserialize(JsonNode body, Type type) throws JacksonException {
        return jsonMapper.treeToValue(body, jsonMapper.constructType(type));
    }
}
