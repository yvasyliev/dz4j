package io.github.yvasyliev.deezer.v2.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.v2.methods.Method;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MethodDeserializer extends AbstractMethodDeserializer<Method<?>> {
    private final Map<Pattern, Class<? extends Method<?>>> classMap;

    @Builder
    public static MethodDeserializer create(@Singular("classMap") Map<String, Class<? extends Method<?>>> classMap) {
        return classMap
                .entrySet()
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                entry -> Pattern.compile(entry.getKey()),
                                Map.Entry::getValue
                        ),
                        MethodDeserializer::new
                ));
    }

    @Override
    public Method<?> deserialize(JsonDeserializationContext context, String path, JsonObject queryParams) throws JsonParseException {
        for (Map.Entry<Pattern, Class<? extends Method<?>>> classEntry : classMap.entrySet()) {
            Matcher matcher = classEntry.getKey().matcher(path);
            if (matcher.matches()) {
                if (matcher.groupCount() > 0) {
                    queryParams.addProperty(Method.OBJECT_ID, matcher.group(1));
                }
                return context.deserialize(queryParams, classEntry.getValue());
            }
        }
        return null;
    }
}
