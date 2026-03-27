package io.github.yvasyliev.dz4j.feign.codec;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A custom Feign encoder that transforms collection values into CSV strings for form URL-encoded requests.
 */
@RequiredArgsConstructor
public class DeezerFormEncoder implements Encoder {
    private final Encoder delegate;

    /**
     * Encodes the given body using the delegate encoder. If the body contains {@link Collection} values, they will be
     * replaced with a CSV string.
     *
     * @param body     {@inheritDoc}
     * @param bodyType {@inheritDoc}
     * @param template {@inheritDoc}
     * @throws EncodeException {@inheritDoc}
     */
    @Override
    public void encode(Object body, Type bodyType, RequestTemplate template) throws EncodeException {
        delegate.encode(transform(body, template), bodyType, template);
    }

    private Object transform(Object body, RequestTemplate template) {
        return body instanceof Map<?, ?> map && isFormUrlEncoded(template) ? transform(map) : body;
    }

    private Map<?, Object> transform(Map<?, ?> source) {
        return source.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> transform(entry.getValue())));
    }

    private Object transform(Object value) {
        return value instanceof Collection<?> collection
                ? collection.stream().filter(Objects::nonNull).map(String::valueOf).collect(Collectors.joining(","))
                : value;
    }

    private boolean isFormUrlEncoded(RequestTemplate template) {
        return template.headers()
                .entrySet()
                .stream()
                .filter(header -> "Content-Type".equalsIgnoreCase(header.getKey()))
                .flatMap(header -> header.getValue().stream())
                .anyMatch(value -> value.startsWith("application/x-www-form-urlencoded"));
    }
}
