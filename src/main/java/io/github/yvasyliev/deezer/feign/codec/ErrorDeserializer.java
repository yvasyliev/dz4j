package io.github.yvasyliev.deezer.feign.codec;

import io.github.yvasyliev.deezer.exception.AbstractDeezerApiException;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;

import java.lang.reflect.Type;

/**
 * A {@link JsonNode} deserializer that throws an {@link AbstractDeezerApiException} if the node contains an
 * {@code error} object.
 */
@RequiredArgsConstructor
public class ErrorDeserializer implements JsonNodeDeserializer {
    private final JsonNodeDeserializer delegate;

    /**
     * Throws {@link AbstractDeezerApiException} if the node contains an {@code error} object, otherwise returns
     * {@code null}.
     *
     * @param node {@inheritDoc}
     * @param type {@inheritDoc}
     * @param <T>  {@inheritDoc}
     * @return {@code null} if the node does not contain an {@code error} object
     * @throws JacksonException           {@inheritDoc}
     * @throws AbstractDeezerApiException {@inheritDoc}
     */
    @Override
    public <T> T deserialize(JsonNode node, Type type) throws JacksonException, AbstractDeezerApiException {
        var error = node.path("error");

        if (error.isMissingNode() || !error.isObject()) {
            return null;
        }

        throw (AbstractDeezerApiException) delegate.deserialize(error, AbstractDeezerApiException.class);
    }
}
