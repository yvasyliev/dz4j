package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import feign.codec.Decoder;
import io.github.yvasyliev.deezer.exception.DeezerApiException;
import io.github.yvasyliev.deezer.exception.ResponseValidationException;
import tools.jackson.core.JacksonException;

import java.io.IOException;
import java.lang.reflect.Type;

@FunctionalInterface
public interface DeezerDecoder {
    Object decode(Response response, Type type, Decoder chain)
            throws IOException, JacksonException, ResponseValidationException, DeezerApiException;
}
