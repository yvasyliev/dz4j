package io.github.yvasyliev.deezer.databind.cfg;

import io.github.yvasyliev.deezer.databind.util.ExpiresConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.mockito.Mockito;
import tools.jackson.databind.util.Converter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeezerHandlerInstantiatorTest {
    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> testConverterInstance = () -> Stream.of(
            arguments(String.class, null),
            arguments(DeezerHandlerInstantiatorTest.class, mock(Converter.class))
    );

    @ParameterizedTest
    @FieldSource
    void testConverterInstance(Class<?> implClass, Converter<?, ?> expected) {
        var converters = new HashMap<Class<?>, Converter<?, ?>>();

        converters.put(implClass, expected);

        var handlerInstantiator = new DeezerHandlerInstantiator(converters);
        var actual = handlerInstantiator.converterInstance(mock(), mock(), implClass);

        assertEquals(expected, actual);
    }

    @Test
    void testDeserializerInstance() {
        var actual = new DeezerHandlerInstantiator(mock()).deserializerInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testKeyDeserializerInstance() {
        var actual = new DeezerHandlerInstantiator(mock()).keyDeserializerInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testSerializerInstance() {
        var actual = new DeezerHandlerInstantiator(mock()).serializerInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testTypeResolverBuilderInstance() {
        var actual = new DeezerHandlerInstantiator(mock()).typeResolverBuilderInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void testTypeIdResolverInstance() {
        var actual = new DeezerHandlerInstantiator(mock()).typeIdResolverInstance(mock(), mock(), String.class);

        assertNull(actual);
    }

    @Test
    void shouldCreateDeezerHandlerInstantiator() {
        var expiresConverter = Mockito.<Consumer<ExpiresConverter.ExpiresConverterBuilder>>mock();
        var converters = Mockito.<Consumer<Map<Class<?>, Converter<?, ?>>>>mock();
        var expected = new DeezerHandlerInstantiator(Map.of(
                ExpiresConverter.class, ExpiresConverter.builder().build())
        );
        var actual = DeezerHandlerInstantiator.builder()
                .expiresConverter(expiresConverter)
                .converters(converters)
                .build();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(expiresConverter).accept(notNull());
        verify(converters).accept(notNull());
    }
}
