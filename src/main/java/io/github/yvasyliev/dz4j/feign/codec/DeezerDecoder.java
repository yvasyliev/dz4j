package io.github.yvasyliev.dz4j.feign.codec;

import feign.Response;
import feign.codec.Decoder;
import io.github.yvasyliev.dz4j.exception.DeezerException;
import io.github.yvasyliev.dz4j.util.Customizer;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Decoder for Deezer API responses.
 */
@RequiredArgsConstructor
public class DeezerDecoder implements Decoder {
    private final List<ResponseValidator> responseValidators;
    private final List<JsonNodeDeserializer> jsonNodeDeserializers;
    private final JsonMapper jsonMapper;

    @Builder
    private DeezerDecoder(
            JsonMapper jsonMapper,
            @Nullable Consumer<List<ResponseValidator>> responseValidators,
            @Nullable Consumer<List<JsonNodeDeserializer>> jsonNodeDeserializers
    ) {
        this(new ArrayList<>(), new ArrayList<>(), jsonMapper);

        this.responseValidators.add(new HeadersValidator());
        this.responseValidators.add(new BodyValidator());
        this.responseValidators.add(new AccessTokenValidator());

        Customizer.customize(this.responseValidators, responseValidators);

        var defaultDeserializer = new DefaultDeserializer(jsonMapper);

        this.jsonNodeDeserializers.add(new ErrorDeserializer(defaultDeserializer));
        this.jsonNodeDeserializers.add(defaultDeserializer);

        Customizer.customize(this.jsonNodeDeserializers, jsonNodeDeserializers);
    }

    /**
     * Decodes the response from the Deezer API.
     *
     * @param response {@inheritDoc}
     * @param type     {@inheritDoc}
     * @return the decoded response body
     * @throws IOException      if an I/O error occurs while reading the response body
     * @throws JacksonException if an error occurs while deserializing the response body
     * @throws DeezerException  if the response is invalid according to the validators or if no deserializer can handle
     *                          the response body
     */
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
