package io.github.yvasyliev.deezer.databind.cfg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.databind.cfg.HandlerInstantiator;
import tools.jackson.databind.util.Converter;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DeezerHandlerInstantiatorTest {
    @Mock private Converter<?, ?> converter;
    private HandlerInstantiator handlerInstantiator;

    @BeforeEach
    void setUp() {
        handlerInstantiator = new DeezerHandlerInstantiator(Map.of(DeezerHandlerInstantiatorTest.class, converter));
    }

    @Test
    void shouldReturnConverter() {
        var actual = handlerInstantiator.converterInstance(mock(), mock(), DeezerHandlerInstantiatorTest.class);

        assertEquals(converter, actual);
    }

    @Test
    void shouldReturnNullForUnknownConverter() {
        var actual = handlerInstantiator.converterInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testDeserializerInstance() {
        var actual = handlerInstantiator.deserializerInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testKeyDeserializerInstance() {
        var actual = handlerInstantiator.keyDeserializerInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testSerializerInstance() {
        var actual = handlerInstantiator.serializerInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testTypeResolverBuilderInstance() {
        var actual = handlerInstantiator.typeResolverBuilderInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testTypeIdResolverInstance() {
        var actual = handlerInstantiator.typeIdResolverInstance(mock(), mock(), String.class);

        assertNull(actual);
    }
}
