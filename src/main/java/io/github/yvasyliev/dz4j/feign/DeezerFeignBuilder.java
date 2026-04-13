package io.github.yvasyliev.dz4j.feign;

import feign.AsyncFeign;
import feign.form.FormEncoder;
import io.github.yvasyliev.dz4j.databind.json.DeezerJsonMapperBuilder;
import io.github.yvasyliev.dz4j.feign.codec.DeezerDecoder;
import io.github.yvasyliev.dz4j.feign.codec.DeezerFormEncoder;
import io.github.yvasyliev.dz4j.util.Customizer;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.json.JsonMapper;

import java.util.function.Consumer;

/**
 * Builder for creating a Feign client for the Deezer API.
 */
@Setter
@Accessors(fluent = true)
public class DeezerFeignBuilder {
    @Nullable private Consumer<DeezerJsonMapperBuilder> jsonMapper;
    @Nullable private Consumer<DeezerDecoder.DeezerDecoderBuilder> decoder;
    @Nullable private Consumer<DeezerContract.DeezerContractBuilder> contract;
    @Nullable private Consumer<AsyncFeign.AsyncBuilder<Object>> feign;

    /**
     * Builds a Feign client for the Deezer API using the configured JSON mapper, decoder, contract, and Feign builder.
     *
     * @return a Feign client for the Deezer API
     */
    public AsyncFeign.AsyncBuilder<Object> build() {
        var mapper = buildJsonMapper();
        var feignBuilder = AsyncFeign.builder()
                .dismiss404()
                .encoder(new DeezerFormEncoder(new FormEncoder()))
                .decoder(buildDecoder(mapper))
                .contract(buildContract(mapper));

        return Customizer.customize(feignBuilder, feign);
    }

    private JsonMapper buildJsonMapper() {
        return Customizer.customize(new DeezerJsonMapperBuilder(), jsonMapper).build();
    }

    private DeezerDecoder buildDecoder(JsonMapper mapper) {
        return Customizer.customize(DeezerDecoder.builder().jsonMapper(mapper), decoder).build();
    }

    private DeezerContract buildContract(JsonMapper mapper) {
        return Customizer.customize(DeezerContract.builder().jsonMapper(mapper), contract).build();
    }
}
