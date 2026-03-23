package io.github.yvasyliev.deezer.feign;

import feign.AsyncFeign;
import io.github.yvasyliev.deezer.databind.json.DeezerJsonMapperBuilder;
import io.github.yvasyliev.deezer.feign.codec.DeezerDecoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeezerFeignBuilderTest {
    @Mock private Consumer<DeezerJsonMapperBuilder> jsonMapperCustomizer;
    @Mock private Consumer<DeezerDecoder.DeezerDecoderBuilder> decoderCustomizer;
    @Mock private Consumer<DeezerContract.DeezerContractBuilder> contractCustomizer;
    @Mock private Consumer<AsyncFeign.AsyncBuilder<Object>> feignCustomizer;

    @Test
    void testBuild() {
        var feign = new DeezerFeignBuilder()
                .jsonMapper(jsonMapperCustomizer)
                .decoder(decoderCustomizer)
                .contract(contractCustomizer)
                .feign(this.feignCustomizer)
                .build();

        assertNotNull(feign);
        verify(jsonMapperCustomizer).accept(any());
        verify(decoderCustomizer).accept(any());
        verify(contractCustomizer).accept(any());
        verify(this.feignCustomizer).accept(any());
    }
}
