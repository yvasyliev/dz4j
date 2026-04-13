package io.github.yvasyliev.dz4j.databind.deser.std;

import org.jspecify.annotations.Nullable;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.DelegatingDeserializer;

/**
 * Delegating deserializer that returns {@code null} if the current token is {@code false}, and delegates
 * deserialization otherwise.
 */
public class FalseToNullDelegatingDeserializer extends DelegatingDeserializer {
    public FalseToNullDelegatingDeserializer(ValueDeserializer<?> d) {
        super(d);
    }

    @Override
    protected ValueDeserializer<?> newDelegatingInstance(ValueDeserializer<?> newDelegatee) {
        return new FalseToNullDelegatingDeserializer(newDelegatee);
    }

    /**
     * Returns {@code null} if current token is {@code false} or passes the deserialization to the delegate.
     *
     * @param p    {@inheritDoc}
     * @param ctxt {@inheritDoc}
     * @return {@code null} if current token is {@code false}, deserialized value from the delegate otherwise
     * @throws JacksonException if deserialization error occurs
     */
    @Override
    @Nullable
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        return JsonToken.VALUE_FALSE.equals(p.currentToken()) ? null : super.deserialize(p, ctxt);
    }
}
