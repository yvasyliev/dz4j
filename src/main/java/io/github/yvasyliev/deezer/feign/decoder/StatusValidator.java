package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

public class StatusValidator implements ResponseValidator {
    @Override
    public void validate(Response response, Type type) throws ResponseException {
        if (response.status() != HttpURLConnection.HTTP_OK) {
            throw new ResponseException("Invalid response status: " + response.status(), response);
        }
    }
}
