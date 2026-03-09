package io.github.yvasyliev.deezer.databind.deser.std;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.DelegatingDeserializer;

public class FalseToNullDelegatingDeserializer extends DelegatingDeserializer {
    public FalseToNullDelegatingDeserializer(ValueDeserializer<?> d) {
        super(d);
    }

    @Override
    protected ValueDeserializer<?> newDelegatingInstance(ValueDeserializer<?> newDelegatee) {
        return new FalseToNullDelegatingDeserializer(newDelegatee);
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        return JsonToken.VALUE_FALSE.equals(p.currentToken()) ? null : super.deserialize(p, ctxt);
    }
}
