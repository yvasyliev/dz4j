package io.github.yvasyliev.deezer.v2.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;

import java.util.Map;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvancedSearchMethodDeserializer extends AbstractMethodDeserializer<AdvancedSearchMethod<?>> {
    @Singular("classMap")
    private final Map<String, Class<? extends AdvancedSearchMethod<?>>> classMap;


    @Override
    public AdvancedSearchMethod<?> deserialize(JsonDeserializationContext context, String path, JsonObject queryParams) throws JsonParseException {
        for (Map.Entry<String, Class<? extends AdvancedSearchMethod<?>>> classEntry : classMap.entrySet()) {
            if (classEntry.getKey().equals(path)) {
                return context.deserialize(queryParams, classEntry.getValue());
            }
        }
        return null;
    }
}
