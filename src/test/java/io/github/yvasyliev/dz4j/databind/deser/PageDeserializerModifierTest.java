package io.github.yvasyliev.dz4j.databind.deser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.yvasyliev.dz4j.databind.deser.std.FalseToNullDelegatingDeserializer;
import io.github.yvasyliev.dz4j.model.Page;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.mockito.Mockito;
import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.ValueDeserializerModifier;

class PageDeserializerModifierTest {
    private static final ValueDeserializerModifier PAGE_DESERIALIZER_MODIFIER = new PageDeserializerModifier();

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> testModifyDeserializer = () -> {
        var deserializer = mock(ValueDeserializer.class);

        when(deserializer.handledType()).thenReturn(Object.class);

        // region tc1

        var beanDescRef1 = mock(BeanDescription.Supplier.class);
        var beanDescription1 = mock(BeanDescription.class);
        var expected1 = new FalseToNullDelegatingDeserializer(deserializer);

        when(beanDescRef1.get()).thenReturn(beanDescription1);
        Mockito.<Class<?>>when(beanDescription1.getBeanClass()).thenReturn(Page.class);

        // endregion

        // region tc2

        var beanDescRef2 = mock(BeanDescription.Supplier.class);
        var beanDescription2 = mock(BeanDescription.class);

        when(beanDescRef2.get()).thenReturn(beanDescription2);
        Mockito.<Class<?>>when(beanDescription2.getBeanClass()).thenReturn(String.class);

        // endregion

        return Stream.of(
                arguments(beanDescRef1, deserializer, expected1), arguments(beanDescRef2, deserializer, deserializer));
    };

    @ParameterizedTest
    @FieldSource
    void testModifyDeserializer(
            BeanDescription.Supplier beanDescRef, ValueDeserializer<?> deserializer, ValueDeserializer<?> expected) {
        var actual = PAGE_DESERIALIZER_MODIFIER.modifyDeserializer(mock(), beanDescRef, deserializer);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
