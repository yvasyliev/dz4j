package io.github.yvasyliev.deezer.exception;

import feign.Response;

public class InvalidResponseStatusException extends ResponseValidationException {
    public InvalidResponseStatusException(Response response) {
        super("Response status %d is not supported".formatted(response.status()), response);
    }
}
