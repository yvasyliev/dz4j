package io.github.yvasyliev.deezer.util;

import feign.AsyncFeign;
import feign.form.FormEncoder;
import io.github.yvasyliev.deezer.feign.DeezerContract;
import io.github.yvasyliev.deezer.feign.decoder.DeezerDecoder;
import lombok.experimental.UtilityClass;
import tools.jackson.databind.json.JsonMapper;

import java.util.function.Consumer;

@UtilityClass
public class FeignConfigurator {
    public AsyncFeign.AsyncBuilder<Object> build(
            JsonMapper jsonMapper,
            Consumer<DeezerDecoder.DeezerDecoderBuilder> decoderCustomizer,
            Consumer<DeezerContract.DeezerContractBuilder> contractCustomizer,
            Consumer<AsyncFeign.AsyncBuilder<Object>> feignCustomizer
    ) {
        var decoder = DeezerDecoder.builder()
                .responseValidators(DeezerDefaults.responseValidators())
                .jsonNodeDeserializers(DeezerDefaults.jsonNodeDeserializers(jsonMapper))
                .jsonMapper(jsonMapper);

        decoderCustomizer.accept(decoder);

        var contract = DeezerContract.builder().expanders(DeezerDefaults.expanders(jsonMapper));

        contractCustomizer.accept(contract);

        var builder = AsyncFeign.builder()
                .encoder(new FormEncoder())
                .decoder(decoder.build())
                .contract(contract.build());

        feignCustomizer.accept(builder);

        return builder;
    }
}
