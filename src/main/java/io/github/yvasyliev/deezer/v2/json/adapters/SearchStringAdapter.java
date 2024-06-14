package io.github.yvasyliev.deezer.v2.json.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class SearchStringAdapter implements JsonDeserializer<String>, JsonSerializer<String> {
    private static final String QUOTATION_MARK = "\"";

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String searchString = json.getAsString();
        return searchString.substring(1, searchString.length() - 1);
    }

    @Override
    public JsonElement serialize(String searchString, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(QUOTATION_MARK + searchString + QUOTATION_MARK);
    }
}
