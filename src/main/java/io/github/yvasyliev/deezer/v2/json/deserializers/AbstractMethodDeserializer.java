package io.github.yvasyliev.deezer.v2.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.net.URI;

public abstract class AbstractMethodDeserializer<T> implements JsonDeserializer<T> {
    public abstract T deserialize(JsonDeserializationContext context, String path, JsonObject queryParams) throws JsonParseException;

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        URI uri = URI.create(json.getAsString());
        String path = uri.getPath();
        JsonObject queryParams = getQueryParams(uri.getQuery());
        T method = deserialize(context, path, queryParams);
        if (method == null) {
            throw new JsonParseException("No deserializer was found found for " + uri);
        }
        return method;
    }

    protected JsonObject getQueryParams(String query) {
        JsonObject queryParams = new JsonObject();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] keyVal = param.split("=");
                queryParams.addProperty(keyVal[0], keyVal[1]);
            }
        }
        return queryParams;
    }
}
