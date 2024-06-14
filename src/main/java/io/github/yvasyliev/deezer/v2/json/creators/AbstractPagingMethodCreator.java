package io.github.yvasyliev.deezer.v2.json.creators;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonParseException;
import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@Setter
public abstract class AbstractPagingMethodCreator<S extends DeezerService> implements InstanceCreator<Object> {
    protected Gson gson;
    protected S deezerService;

    @Override
    public Object createInstance(Type type) {
        try {
            return createInstance((Class<?>) type);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new JsonParseException(e);
        }
    }

    public abstract Object createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
