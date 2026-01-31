package io.github.yvasyliev.deezer.exception;

import feign.Response;

public class NoResponseHeadersException extends ResponseValidationException {
    public NoResponseHeadersException(Response response) {
        super("Response has no headers", response);
    }
}
