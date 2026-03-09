package io.github.yvasyliev.deezer.feign.decoder;

import io.github.yvasyliev.deezer.exception.DeezerApiException;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class ErrorDeserializer implements JsonNodeDeserializer {
    private final JsonNodeDeserializer delegate;

    @Override
    public <T> T deserialize(JsonNode node, Type type) throws JacksonException, DeezerApiException {
        var error = node.path("error");

        if (error.isMissingNode()) {
            return null;
        }

        throw (DeezerApiException) delegate.deserialize(error, DeezerApiException.class);
    }
}
