package io.github.yvasyliev.deezer.feign.decoder;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultDeserializerTest {
    private static final JsonMapper MAPPER = new JsonMapper();
    private static final JsonNodeDeserializer DESERIALIZER = new DefaultDeserializer(MAPPER);

    @Test
    void testDeserialize() {
        var expected = new Hello("Hello, World!");
        var actual = DESERIALIZER.deserialize(MAPPER.valueToTree(expected), Hello.class);

        assertEquals(expected, actual);
    }

    private record Hello(String message) {}
}