package io.github.yvasyliev.deezer.feign;

import feign.Param;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StrictExpanderTest {
    private static final Param.Expander EXPANDER = new StrictExpander();

    @SuppressWarnings("checkstyle:ConstantName")
    private static final Supplier<Stream<Arguments>> testExpand = () -> Stream.of(
            arguments(true, "on"),
            arguments(false, null),
            arguments("string", null),
            arguments(123, null)
    );

    @ParameterizedTest
    @FieldSource
    void testExpand(Object value, String expected) {
        var actual = EXPANDER.expand(value);

        assertEquals(expected, actual);
    }
}
