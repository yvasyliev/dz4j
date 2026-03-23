package io.github.yvasyliev.deezer.feign.codec;

import feign.Response;
import feign.codec.Decoder;
import io.github.yvasyliev.deezer.exception.DeezerException;
import io.github.yvasyliev.deezer.exception.ResponseException;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeezerDecoderTest {
    @Mock private ResponseValidator responseValidator;
    @Mock private JsonNodeDeserializer jsonNodeDeserializer;
    @Mock private JsonMapper jsonMapper;
    private Decoder decoder;

    @BeforeEach
    void setUp() {
        decoder = new DeezerDecoder(List.of(responseValidator), List.of(jsonNodeDeserializer), jsonMapper);
    }

    @Test
    void shouldBuildDeezerContract() {
        var responseValidators = Mockito.<Consumer<List<ResponseValidator>>>mock();
        var jsonNodeDeserializers = Mockito.<Consumer<List<JsonNodeDeserializer>>>mock();
        var build = DeezerDecoder.builder()
                .responseValidators(responseValidators)
                .jsonNodeDeserializers(jsonNodeDeserializers)
                .jsonMapper(jsonMapper)
                .build();

        assertNotNull(build);
        verify(responseValidators).accept(anyList());
        verify(jsonNodeDeserializers).accept(anyList());
    }

    @Test
    void testDecode() throws IOException {
        var body = mock(Response.Body.class);
        @Cleanup var inputStream = mock(InputStream.class);
        @Cleanup var response = Response.builder().request(mock()).body(body).build();
        var type = mock(Type.class);
        var jsonNode = mock(JsonNode.class);
        var expected = mock(Object.class);

        when(body.asInputStream()).thenReturn(inputStream);
        when(jsonMapper.readTree(inputStream)).thenReturn(jsonNode);
        when(jsonNodeDeserializer.deserialize(jsonNode, type)).thenReturn(expected);

        var actual = decoder.decode(response, type);

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowResponseException() {
        @Cleanup var response = mock(Response.class);
        var type = mock(Type.class);

        doThrow(ResponseException.class).when(responseValidator).validate(response, type);

        assertThrows(ResponseException.class, () -> decoder.decode(response, type));
    }

    @Test
    void shouldThrowIOExceptionWhenReadingBody() throws IOException {
        var body = mock(Response.Body.class);
        @Cleanup var response = Response.builder().request(mock()).body(body).build();
        var type = mock(Type.class);

        when(body.asInputStream()).thenThrow(IOException.class);

        assertThrows(IOException.class, () -> decoder.decode(response, type));
    }

    @Test
    void shouldThrowIOExceptionWhenDeserializingBody() throws IOException {
        var body = mock(Response.Body.class);
        @Cleanup var inputStream = mock(InputStream.class);
        @Cleanup var response = Response.builder().request(mock()).body(body).build();
        var type = mock(Type.class);
        var e = mock(JacksonException.class);

        when(body.asInputStream()).thenReturn(inputStream);
        when(jsonMapper.readTree(inputStream)).thenThrow(e);
        when(e.getCause()).thenReturn(mock(IOException.class));

        assertThrows(IOException.class, () -> decoder.decode(response, type));
    }

    @Test
    void shouldThrowJacksonExceptionWhenDeserializingBody() throws IOException {
        var body = mock(Response.Body.class);
        @Cleanup var inputStream = mock(InputStream.class);
        @Cleanup var response = Response.builder().request(mock()).body(body).build();
        var type = mock(Type.class);
        var e = mock(JacksonException.class);

        when(body.asInputStream()).thenReturn(inputStream);
        when(jsonMapper.readTree(inputStream)).thenThrow(e);

        assertThrows(JacksonException.class, () -> decoder.decode(response, type));
    }

    @Test
    void shouldThrowDeezerExceptionWhenNoDeserializerFound() throws IOException {
        var body = mock(Response.Body.class);
        @Cleanup var inputStream = mock(InputStream.class);
        @Cleanup var response = Response.builder().request(mock()).body(body).build();
        var type = mock(Type.class);

        when(body.asInputStream()).thenReturn(inputStream);
        when(jsonMapper.readTree(inputStream)).thenReturn(mock(JsonNode.class));

        assertThrows(DeezerException.class, () -> decoder.decode(response, type));
    }
}
