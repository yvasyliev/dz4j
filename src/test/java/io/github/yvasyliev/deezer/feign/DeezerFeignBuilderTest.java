package io.github.yvasyliev.deezer.feign;

import feign.AsyncFeign;
import io.github.yvasyliev.deezer.databind.json.DeezerJsonMapperBuilder;
import io.github.yvasyliev.deezer.feign.decoder.DeezerDecoder;
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
    @Mock private Consumer<DeezerJsonMapperBuilder> jsonMapper;
    @Mock private Consumer<DeezerDecoder.DeezerDecoderBuilder> decoder;
    @Mock private Consumer<DeezerContract.DeezerContractBuilder> contract;
    @Mock private Consumer<AsyncFeign.AsyncBuilder<Object>> feign;

    @Test
    void testBuild() {
        var feign = new DeezerFeignBuilder()
                .jsonMapper(jsonMapper)
                .decoder(decoder)
                .contract(contract)
                .feign(this.feign)
                .build();

        assertNotNull(feign);
        verify(jsonMapper).accept(any());
        verify(decoder).accept(any());
        verify(contract).accept(any());
        verify(this.feign).accept(any());
    }
}
