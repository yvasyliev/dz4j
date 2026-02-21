package io.github.yvasyliev.deezer.exception;

import feign.Response;

public class AccessTokenResponseException extends ResponseException {
    public AccessTokenResponseException(String message, Response response) {
        super(message, response);
    }
}
