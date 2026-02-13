package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;

import java.lang.reflect.Type;

public class HeadersValidator implements ResponseValidator {
    @Override
    public void validate(Response response, Type type) throws ResponseException {
        var headers = response.headers();

        if (headers == null) {
            throw new ResponseException("No response headers present", response);
        }

        if (!headers.containsKey("content-type")) {
            throw new ResponseException("No Content-Type response header present", response);
        }
    }
}
