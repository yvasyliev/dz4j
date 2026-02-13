package io.github.yvasyliev.deezer.feign.decoder;

import io.github.yvasyliev.deezer.exception.DeezerApiException;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class ErrorDeserializer implements JsonNodeDeserializer {
    private final JsonMapper jsonMapper;

    @Override
    public Object deserialize(JsonNode node, Type type) throws JacksonException, DeezerApiException {
        var error = node.path("error");

        if (error.isMissingNode()) {
            return null;
        }

        throw jsonMapper.treeToValue(error, DeezerApiException.class);
    }
}
