package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;

import java.lang.reflect.Type;

@FunctionalInterface
public interface ResponseValidator {
    void validate(Response response, Type type) throws ResponseException;
}
