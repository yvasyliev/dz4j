package io.github.yvasyliev.deezer.feign.codec;

import feign.RequestTemplate;
import feign.codec.Encoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeezerFormEncoderTest {
    @InjectMocks private DeezerFormEncoder encoder;
    @Mock private Encoder delegate;

    @Test
    void shouldDoNothingIfTheBodyIsNotMap() {
        var body = "test";
        var bodyType = String.class;
        var requestTemplate = mock(RequestTemplate.class);

        encoder.encode(body, bodyType, requestTemplate);

        verify(delegate).encode(body, bodyType, requestTemplate);
    }

    @Test
    void shouldDonnNothingIfContentTypeIsNotFormUrlEncoded() {
        var body = Map.of("key", "value");
        var bodyType = new TypeReference<Map<String, String>>() {}.getType();
        var requestTemplate = new RequestTemplate().header("Content-Type", "application/json");

        encoder.encode(body, bodyType, requestTemplate);

        verify(delegate).encode(body, bodyType, requestTemplate);
    }

    @Test
    void shouldTransformBody() {
        var body = Map.of(
                "key1", "single value",
                "key2", List.of("value1", "value2")
        );
        var bodyType = new TypeReference<Map<String, Object>>() {}.getType();
        var requestTemplate = new RequestTemplate().header("Content-Type", "application/x-www-form-urlencoded");
        var expected = Map.of(
                "key1", "single value",
                "key2", "value1,value2"
        );

        encoder.encode(body, bodyType, requestTemplate);

        verify(delegate).encode(expected, bodyType, requestTemplate);
    }
}
