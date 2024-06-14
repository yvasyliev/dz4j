package io.github.yvasyliev.deezer.v2.json.creators;

import com.google.gson.Gson;
import io.github.yvasyliev.deezer.service.DeezerService;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

@Setter
public class PagingMethodCreator<S extends DeezerService> extends AbstractPagingMethodCreator<S> {
    protected static final long DEFAULT_OBJECT_ID = 0;

    @Override
    public Object createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return clazz.getConstructor(Gson.class, deezerService.getClass().getInterfaces()[0], long.class).newInstance(gson, deezerService, DEFAULT_OBJECT_ID);
    }
}
