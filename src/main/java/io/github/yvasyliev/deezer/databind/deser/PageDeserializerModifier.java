package io.github.yvasyliev.deezer.databind.deser;

import io.github.yvasyliev.deezer.databind.deser.std.PageDelegatingDeserializer;
import io.github.yvasyliev.deezer.model.Page;
import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.ValueDeserializerModifier;

public class PageDeserializerModifier extends ValueDeserializerModifier {
    @Override
    public ValueDeserializer<?> modifyDeserializer(
            DeserializationConfig config,
            BeanDescription.Supplier beanDescRef,
            ValueDeserializer<?> deserializer
    ) {
        return Page.class.isAssignableFrom(beanDescRef.get().getBeanClass())
                ? new PageDelegatingDeserializer(deserializer)
                : super.modifyDeserializer(config, beanDescRef, deserializer);
    }
}
