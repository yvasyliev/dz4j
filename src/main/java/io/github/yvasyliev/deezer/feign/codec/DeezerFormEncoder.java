package io.github.yvasyliev.deezer.feign.codec;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DeezerFormEncoder implements Encoder {
    private final Encoder delegate;

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
