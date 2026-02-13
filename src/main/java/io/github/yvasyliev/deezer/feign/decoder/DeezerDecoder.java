package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import feign.codec.Decoder;
import io.github.yvasyliev.deezer.exception.DeezerException;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class DeezerDecoder implements Decoder {
    private final List<ResponseValidator> responseValidators;
    private final List<JsonNodeDeserializer> jsonNodeDeserializers;
    private final JsonMapper jsonMapper;

    @Override
    public Object decode(Response response, Type type) throws IOException, JacksonException, DeezerException {
        responseValidators.forEach(validator -> validator.validate(response, type));

        return deserializeBody(response, type);
    }

    private Object deserializeBody(Response response, Type type) throws IOException, JacksonException, DeezerException {
        try {
            return deserializeBody(readBody(response), type);
        } catch (JacksonException e) {
            if (e.getCause() instanceof IOException ex) {
                throw ex;
            }

            throw e;
        }
    }

    private Object deserializeBody(JsonNode body, Type type) throws JacksonException, DeezerException {
        return jsonNodeDeserializers.stream()
                .map(deserializer -> deserializer.deserialize(body, type))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new DeezerException(
                        "No deserializer found for body: %s, type: %s".formatted(body, type)
                ));
    }

    private JsonNode readBody(Response response) throws IOException {
        return jsonMapper.readTree(response.body().asInputStream());
    }
}
