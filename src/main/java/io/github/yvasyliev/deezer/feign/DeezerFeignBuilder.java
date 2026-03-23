package io.github.yvasyliev.deezer.feign;

import feign.AsyncFeign;
import feign.form.FormEncoder;
import io.github.yvasyliev.deezer.databind.json.DeezerJsonMapperBuilder;
import io.github.yvasyliev.deezer.feign.codec.DeezerFormEncoder;
import io.github.yvasyliev.deezer.feign.codec.DeezerDecoder;
import io.github.yvasyliev.deezer.util.Customizer;
import lombok.Setter;
import lombok.experimental.Accessors;
import tools.jackson.databind.json.JsonMapper;

import java.util.function.Consumer;

/**
 * Builder for creating a Feign client for the Deezer API.
 */
@Setter
@Accessors(fluent = true)
public class DeezerFeignBuilder {
    private Consumer<DeezerJsonMapperBuilder> jsonMapper;
    private Consumer<DeezerDecoder.DeezerDecoderBuilder> decoder;
    private Consumer<DeezerContract.DeezerContractBuilder> contract;
    private Consumer<AsyncFeign.AsyncBuilder<Object>> feign;

    /**
     * Builds a Feign client for the Deezer API using the configured JSON mapper, decoder, contract, and Feign builder.
     *
     * @return a Feign client for the Deezer API
     */
    public AsyncFeign.AsyncBuilder<Object> build() {
        var jsonMapper = buildJsonMapper();
        var feign = AsyncFeign.builder()
                .dismiss404()
                .encoder(new DeezerFormEncoder(new FormEncoder()))
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
