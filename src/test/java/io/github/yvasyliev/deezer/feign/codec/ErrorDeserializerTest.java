package io.github.yvasyliev.deezer.feign.codec;

import io.github.yvasyliev.deezer.exception.DeezerApiException;
import io.github.yvasyliev.deezer.exception.DeezerApiResponseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.databind.node.JsonNodeFactory;
import tools.jackson.databind.node.MissingNode;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorDeserializerTest {
    @InjectMocks private ErrorDeserializer deserializer;
    @Mock private JsonNodeDeserializer delegate;

    @Test
    void shouldReturnNullIfErrorIsMissing() {
        var actual = deserializer.deserialize(MissingNode.getInstance(), Object.class);

        assertNull(actual);
    }

    @Test
    void shouldReturnNullIfErrorIsNotObject() {
        var node = JsonNodeFactory.instance.objectNode();

        node.put("error", "error");

        var actual = deserializer.deserialize(node, Object.class);

        assertNull(actual);
    }

    @Test
    void shouldThrowDeezerApiException() {
        var node = JsonNodeFactory.instance.objectNode();

        node.putObject("error");

        when(delegate.deserialize(node.path("error"), DeezerApiException.class))
                .thenReturn(mock(DeezerApiResponseException.class));

        assertThrows(DeezerApiException.class, () -> deserializer.deserialize(node, Object.class));
    }
}