package io.github.yvasyliev.deezer.util;

import feign.AsyncFeign;
import feign.Param;
import feign.form.FormEncoder;
import io.github.yvasyliev.deezer.feign.DeezerContract;
import io.github.yvasyliev.deezer.feign.decoder.DeezerDecoder;
import io.github.yvasyliev.deezer.feign.decoder.JsonNodeDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.ResponseValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FeignConfiguratorTest {
    @Mock private JsonMapper jsonMapper;
    @Mock private Consumer<AsyncFeign.AsyncBuilder<Object>> feignCustomizer;

    @Test
    void testBuild() {
        var responseValidators = List.of(mock(ResponseValidator.class));
        var jsonNodeDeserializers = List.of(mock(JsonNodeDeserializer.class));
        var expander = mock(Param.Expander.class);
        var expected = AsyncFeign.builder()
                .dismiss404()
                .encoder(new FormEncoder())
                .decoder(new DeezerDecoder(responseValidators, jsonNodeDeserializers, jsonMapper))
                .contract(new DeezerContract(Map.of(TestExpander.class, expander)));
        var actual = FeignConfigurator.build(
                jsonMapper,
                builder -> builder
                        .responseValidators(responseValidators)
                        .jsonNodeDeserializers(jsonNodeDeserializers),
                builder -> builder.expanders(Map.of(TestExpander.class, expander)),
                feignCustomizer
        );

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(feignCustomizer).accept(any());
    }

    private interface TestExpander extends Param.Expander {}
}
