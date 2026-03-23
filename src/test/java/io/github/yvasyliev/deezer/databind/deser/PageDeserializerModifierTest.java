package io.github.yvasyliev.deezer.databind.deser;

import io.github.yvasyliev.deezer.databind.deser.std.FalseToNullDelegatingDeserializer;
import io.github.yvasyliev.deezer.model.Page;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.ValueDeserializerModifier;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PageDeserializerModifierTest {
    private static final ValueDeserializerModifier PAGE_DESERIALIZER_MODIFIER = new PageDeserializerModifier();

    @SuppressWarnings("checkstyle:ConstantName")
    private static final Supplier<Stream<Arguments>> testModifyDeserializer = () -> {
        var deserializer = mock(ValueDeserializer.class);

        doReturn(Object.class).when(deserializer).handledType();

        //region tc1

        var beanDescRef1 = mock(BeanDescription.Supplier.class);
        var beanDescription1 = mock(BeanDescription.class);
        var expected1 = new FalseToNullDelegatingDeserializer(deserializer);

        when(beanDescRef1.get()).thenReturn(beanDescription1);
        doReturn(Page.class).when(beanDescription1).getBeanClass();

        //endregion

        //region tc2

        var beanDescRef2 = mock(BeanDescription.Supplier.class);
        var beanDescription2 = mock(BeanDescription.class);

        when(beanDescRef2.get()).thenReturn(beanDescription2);
        doReturn(String.class).when(beanDescription2).getBeanClass();

        //endregion

        return Stream.of(
                arguments(beanDescRef1, deserializer, expected1),
                arguments(beanDescRef2, deserializer, deserializer)
        );
    };

    @ParameterizedTest
    @FieldSource
    void testModifyDeserializer(
            BeanDescription.Supplier beanDescRef,
            ValueDeserializer<?> deserializer,
            ValueDeserializer<?> expected
    ) {
        var actual = PAGE_DESERIALIZER_MODIFIER.modifyDeserializer(mock(), beanDescRef, deserializer);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
