package io.github.yvasyliev.deezer.feign;

import feign.AsyncFeign;
import feign.form.FormEncoder;
import io.github.yvasyliev.deezer.databind.json.DeezerJsonMapperBuilder;
import io.github.yvasyliev.deezer.feign.decoder.DeezerDecoder;
import io.github.yvasyliev.deezer.util.Customizer;
import lombok.Setter;
import lombok.experimental.Accessors;
import tools.jackson.databind.json.JsonMapper;

import java.util.function.Consumer;

@Setter
@Accessors(fluent = true)
public class DeezerFeignBuilder {
    private Consumer<DeezerJsonMapperBuilder> jsonMapper;
    private Consumer<DeezerDecoder.DeezerDecoderBuilder> decoder;
    private Consumer<DeezerContract.DeezerContractBuilder> contract;
    private Consumer<AsyncFeign.AsyncBuilder<Object>> feign;

    public AsyncFeign.AsyncBuilder<Object> build() {
        var jsonMapper = buildJsonMapper();
        var feign = AsyncFeign.builder()
                .dismiss404()
                .encoder(new FormEncoder())
                .decoder(buildDecoder(jsonMapper))
                .contract(buildContract(jsonMapper));

        return Customizer.customize(feign, this.feign);
    }

    private JsonMapper buildJsonMapper() {
        return Customizer.customize(new DeezerJsonMapperBuilder(), this.jsonMapper).build();
    }

    private DeezerDecoder buildDecoder(JsonMapper jsonMapper) {
        return Customizer.customize(DeezerDecoder.builder().jsonMapper(jsonMapper), this.decoder).build();
    }

    private DeezerContract buildContract(JsonMapper jsonMapper) {
        return Customizer.customize(DeezerContract.builder().jsonMapper(jsonMapper), this.contract).build();
    }
}
