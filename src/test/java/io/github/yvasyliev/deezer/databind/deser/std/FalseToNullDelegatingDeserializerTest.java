package io.github.yvasyliev.deezer.databind.deser.std;

import lombok.Cleanup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.DelegatingDeserializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FalseToNullDelegatingDeserializerTest {
    @Mock private ValueDeserializer<?> delegatee;
    private DelegatingDeserializer deserializer;

    @BeforeEach
    void setUp() {
        doReturn(Object.class).when(delegatee).handledType();

        deserializer = new FalseToNullDelegatingDeserializer(delegatee);
    }

    @Test
    void shouldDeserializeFalseAsNull() {
        @Cleanup var jsonParser = mock(JsonParser.class);

        when(jsonParser.currentToken()).thenReturn(JsonToken.VALUE_FALSE);

        var actual = deserializer.deserialize(jsonParser, null);

        assertNull(actual);
        verify(delegatee, never()).deserialize(any(), any());
    }

    @Test
    void shouldDelegateDeserialization() {
        @Cleanup var jsonParser = mock(JsonParser.class);
        var ctxt = mock(DeserializationContext.class);
        var expected = mock(Object.class);

        when(jsonParser.currentToken()).thenReturn(JsonToken.VALUE_TRUE);
        doReturn(expected).when(delegatee).deserialize(jsonParser, ctxt);

        var actual = deserializer.deserialize(jsonParser, ctxt);

        assertEquals(expected, actual);
    }

    @Test
    void testNewDelegatingInstance() {
        var newDelegatee = mock(ValueDeserializer.class);

        doReturn(Object.class).when(newDelegatee).handledType();

        var expected = new FalseToNullDelegatingDeserializer(newDelegatee);
        var actual = deserializer.replaceDelegatee(newDelegatee);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
