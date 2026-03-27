package io.github.yvasyliev.dz4j.databind.deser;

import io.github.yvasyliev.dz4j.databind.deser.std.FalseToNullDelegatingDeserializer;
import io.github.yvasyliev.dz4j.model.Page;
import tools.jackson.databind.BeanDescription;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.ValueDeserializerModifier;

/**
 * Custom deserializer modifier for {@link Page} that handles the case when the API returns {@code false} instead of an
 * empty page. In such cases, the deserializer will return {@code null} instead of throwing an exception or returning an
 * empty page. For other types, the deserializer will be left unchanged.
 */
public class PageDeserializerModifier extends ValueDeserializerModifier {
    /**
     * Modifies deserializer for {@link Page} to return {@code null} if the current token is {@code false}, and delegate
     * deserialization otherwise.
     *
     * @param config       deserialization config
     * @param beanDescRef  bean description
     * @param deserializer original deserializer
     * @return modified deserializer for {@link Page} or original deserializer for other types
     */
    @Override
    public ValueDeserializer<?> modifyDeserializer(
            DeserializationConfig config,
            BeanDescription.Supplier beanDescRef,
            ValueDeserializer<?> deserializer
    ) {
        return Page.class.isAssignableFrom(beanDescRef.get().getBeanClass())
                ? new FalseToNullDelegatingDeserializer(deserializer)
                : super.modifyDeserializer(config, beanDescRef, deserializer);
    }
}
