package io.github.yvasyliev.deezer.exception;

import feign.Response;

import java.lang.reflect.Type;

public class DecoderNotFoundException extends ResponseValidationException {
    public DecoderNotFoundException(Response response, Type type) {
        super("Decoder not found for type: " + type, response);
    }
}
