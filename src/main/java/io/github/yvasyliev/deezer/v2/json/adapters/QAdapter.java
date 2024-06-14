package io.github.yvasyliev.deezer.v2.json.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.yvasyliev.deezer.v2.methods.AdvancedSearchMethod;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

public class QAdapter implements JsonDeserializer<AdvancedSearchMethod.Q>, JsonSerializer<AdvancedSearchMethod.Q> {
    private static final String SPACE = " ";
    private static final String COLON = ":";

    @Override
    public AdvancedSearchMethod.Q deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = new JsonObject();
        for (String qParam : json.getAsString().split(SPACE)) {
            String[] keyVal = qParam.split(COLON);
            jsonObject.addProperty(keyVal[0], keyVal[1]);
        }
        return context.deserialize(jsonObject, typeOfT);
    }

    @Override
    public JsonElement serialize(AdvancedSearchMethod.Q src, Type typeOfSrc, JsonSerializationContext context) {
        return context
                .serialize(src)
                .getAsJsonObject()
                .entrySet()
                .stream()
                .map(qParam -> qParam.getKey() + COLON + qParam.getValue().getAsString())
                .collect(Collectors.collectingAndThen(
                        Collectors.joining(SPACE),
                        JsonPrimitive::new
                ));
    }
}
