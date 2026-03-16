package io.github.yvasyliev.deezer.databind.cfg;

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

import java.util.Map;

@RequiredArgsConstructor
public class DeezerHandlerInstantiator extends HandlerInstantiator {
    private final Map<Class<?>, Converter<?, ?>> converters;

    @Override
    public Converter<?, ?> converterInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        return converters.get(implClass);
    }

    @Override
    public ValueDeserializer<?> deserializerInstance(
            DeserializationConfig config,
            Annotated annotated,
            Class<?> deserClass
    ) {
        return null;
    }

    @Override
    public KeyDeserializer keyDeserializerInstance(
            DeserializationConfig config,
            Annotated annotated,
            Class<?> keyDeserClass
    ) {
        return null;
    }

    @Override
    public ValueSerializer<?> serializerInstance(SerializationConfig config, Annotated annotated, Class<?> serClass) {
        return null;
    }

    @Override
    public TypeResolverBuilder<?> typeResolverBuilderInstance(
            MapperConfig<?> config,
            Annotated annotated,
            Class<?> builderClass
    ) {
        return null;
    }

    @Override
    public TypeIdResolver typeIdResolverInstance(MapperConfig<?> config, Annotated annotated, Class<?> resolverClass) {
        return null;
    }
}
