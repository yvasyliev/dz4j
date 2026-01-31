package io.github.yvasyliev.deezer.exception;

import feign.Response;

public class NoContentTypeResponseHeaderException extends ResponseValidationException{
    public NoContentTypeResponseHeaderException(Response response) {
        super("Response has no Content-Type header. Available headers: %s".formatted(response.headers()), response);
    }
}
