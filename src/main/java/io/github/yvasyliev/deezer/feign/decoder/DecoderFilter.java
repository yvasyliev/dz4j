package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import feign.codec.Decoder;
import io.github.yvasyliev.deezer.exception.ResponseValidationException;

import java.io.IOException;
import java.lang.reflect.Type;

@FunctionalInterface
public interface DecoderFilter extends DeezerDecoder {
    @Override
    default Object decode(Response response, Type type, Decoder chain)
            throws IOException, ResponseValidationException {
        filter(response, type);

        return chain.decode(response, type);
    }

    void filter(Response response, Type type) throws IOException, ResponseValidationException;
}
