package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;

import java.lang.reflect.Type;

public class BodyValidator implements ResponseValidator {
    @Override
    public void validate(Response response, Type type) throws ResponseException {
        if (response.body() == null) {
            throw new ResponseException("Body is empty", response);
        }
    }
}
