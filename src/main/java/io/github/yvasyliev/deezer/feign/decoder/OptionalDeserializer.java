package io.github.yvasyliev.deezer.feign.decoder;

import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

@RequiredArgsConstructor
public class OptionalDeserializer implements JsonNodeDeserializer {
    private final JsonMapper jsonMapper;

    @Override
    public Object deserialize(JsonNode body, Type type) throws JacksonException {
        return isOptional(type) ? doDeserialize(body, type) : null;
    }

    private boolean isOptional(Type type) {
        return type instanceof ParameterizedType parameterizedType
                && Optional.class.equals(parameterizedType.getRawType());
    }

    private Optional<Object> doDeserialize(JsonNode body, Type type) throws JacksonException {
        var actualType = ((ParameterizedType) type).getActualTypeArguments()[0];

        return Optional.ofNullable(jsonMapper.treeToValue(body, jsonMapper.constructType(actualType)));
    }
}
