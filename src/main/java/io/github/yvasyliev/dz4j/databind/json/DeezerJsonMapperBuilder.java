package io.github.yvasyliev.dz4j.databind.json;

import io.github.yvasyliev.dz4j.databind.cfg.DeezerHandlerInstantiator;
import io.github.yvasyliev.dz4j.databind.deser.PageDeserializerModifier;
import io.github.yvasyliev.dz4j.databind.util.ZeroToNullLocalDateConverter;
import io.github.yvasyliev.dz4j.databind.util.ZeroToNullLocalDateTimeConverter;
import io.github.yvasyliev.dz4j.util.Customizer;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.cfg.HandlerInstantiator;
import tools.jackson.databind.deser.std.StdConvertingDeserializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Consumer;

/**
 * A builder for creating a customized {@link JsonMapper} instance for Deezer API responses.
 */
@Setter
@Accessors(fluent = true)
public class DeezerJsonMapperBuilder {
    @Nullable private Consumer<SimpleModule> module;
    @Nullable private Consumer<JsonMapper.Builder> mapper;
    @Nullable private Consumer<DeezerHandlerInstantiator.DeezerHandlerInstantiatorBuilder> handlerInstantiator;

    /**
     * Builds a {@link JsonMapper} with the custom configurations provided through the builder.
     *
     * @return a configured {@link JsonMapper} instance
     */
    public JsonMapper build() {
        var builder = JsonMapper.builder()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .handlerInstantiator(buildHandlerInstantiator())
                .addModule(buildModule());

        return Customizer.customize(builder, mapper).build();
    }

    private JacksonModule buildModule() {
        var deezerModule = new SimpleModule("deezer-api")
                .setDeserializerModifier(new PageDeserializerModifier())
                .addDeserializer(
                        LocalDate.class,
                        new StdConvertingDeserializer<>(new ZeroToNullLocalDateConverter())
                )
                .addDeserializer(
                        LocalDateTime.class,
                        new StdConvertingDeserializer<>(new ZeroToNullLocalDateTimeConverter())
                );

        return Customizer.customize(deezerModule, module);
    }

    private HandlerInstantiator buildHandlerInstantiator() {
        return Customizer.customize(DeezerHandlerInstantiator.builder(), handlerInstantiator).build();
    }
}
