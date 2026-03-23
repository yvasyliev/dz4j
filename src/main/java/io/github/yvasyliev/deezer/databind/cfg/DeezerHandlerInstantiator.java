package io.github.yvasyliev.deezer.databind.cfg;

import io.github.yvasyliev.deezer.databind.util.ExpiresConverter;
import io.github.yvasyliev.deezer.util.Customizer;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.KeyDeserializer;
import tools.jackson.databind.SerializationConfig;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.cfg.HandlerInstantiator;
import tools.jackson.databind.cfg.MapperConfig;
import tools.jackson.databind.introspect.Annotated;
import tools.jackson.databind.jsontype.TypeIdResolver;
import tools.jackson.databind.jsontype.TypeResolverBuilder;
import tools.jackson.databind.util.Converter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A custom {@link HandlerInstantiator} that provides custom converters for Jackson.
 */
@RequiredArgsConstructor
public class DeezerHandlerInstantiator extends HandlerInstantiator {
    private final Map<Class<?>, Converter<?, ?>> converters;

    @Builder
    private DeezerHandlerInstantiator(
            Consumer<ExpiresConverter.ExpiresConverterBuilder> expiresConverter,
            Consumer<Map<Class<?>, Converter<?, ?>>> converters
    ) {
        this(new HashMap<>());
        this.converters.put(
                ExpiresConverter.class,
                Customizer.customize(ExpiresConverter.builder(), expiresConverter).build()
        );
        Customizer.customize(this.converters, converters);
    }

    /**
     * Provides converter instance from the {@link #converters} map.
     *
     * @param config    {@inheritDoc}
     * @param annotated {@inheritDoc}
     * @param implClass {@inheritDoc}
     * @return a converter instance or {@code null}
     */
    @Override
    public Converter<?, ?> converterInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        return converters.get(implClass);
    }

    //region ignored

    /**
     * {@inheritDoc}
     *
     * @param config     {@inheritDoc}
     * @param annotated  {@inheritDoc}
     * @param deserClass {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public ValueDeserializer<?> deserializerInstance(
            DeserializationConfig config,
            Annotated annotated,
            Class<?> deserClass
    ) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param config        {@inheritDoc}
     * @param annotated     {@inheritDoc}
     * @param keyDeserClass {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public KeyDeserializer keyDeserializerInstance(
            DeserializationConfig config,
            Annotated annotated,
            Class<?> keyDeserClass
    ) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param config    {@inheritDoc}
     * @param annotated {@inheritDoc}
     * @param serClass  {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public ValueSerializer<?> serializerInstance(SerializationConfig config, Annotated annotated, Class<?> serClass) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param config       {@inheritDoc}
     * @param annotated    {@inheritDoc}
     * @param builderClass {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public TypeResolverBuilder<?> typeResolverBuilderInstance(
            MapperConfig<?> config,
            Annotated annotated,
            Class<?> builderClass
    ) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param config        {@inheritDoc}
     * @param annotated     {@inheritDoc}
     * @param resolverClass {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public TypeIdResolver typeIdResolverInstance(MapperConfig<?> config, Annotated annotated, Class<?> resolverClass) {
        return null;
    }

    //endregion
}
