package io.github.yvasyliev.deezer.exception;

import feign.Response;

public class AccessTokenResponseException extends ResponseValidationException {
    public AccessTokenResponseException(String message, Response response) {
        super(message, response);
    }
}
