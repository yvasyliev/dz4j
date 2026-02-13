package io.github.yvasyliev.deezer.exception;

import feign.Response;

public class AccessTokenException extends ResponseException {
    public AccessTokenException(String message, Response response) {
        super(message, response);
    }
}
