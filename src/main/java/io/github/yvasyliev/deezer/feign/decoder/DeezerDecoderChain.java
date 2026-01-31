package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import feign.codec.Decoder;
import io.github.yvasyliev.deezer.exception.DecoderNotFoundException;
import io.github.yvasyliev.deezer.exception.DeezerApiException;
import io.github.yvasyliev.deezer.exception.ResponseValidationException;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
public class DeezerDecoderChain implements Decoder {
    private static final Decoder NOOP_DECODER = (response, type) -> {
        throw new DecoderNotFoundException(response, type);
    };
    private final DeezerDecoder decoder;
    private final Decoder chain;

    public static Decoder create(List<DeezerDecoder> decoders) {
        return create(0, decoders);
    }

    private static Decoder create(int index, List<DeezerDecoder> decoders) {
        return index < decoders.size()
                ? new DeezerDecoderChain(decoders.get(index), create(index + 1, decoders))
                : NOOP_DECODER;
    }

    @Override
    public Object decode(Response response, Type type)
            throws IOException, JacksonException, ResponseValidationException, DeezerApiException {
        return decoder.decode(response, type, chain);
    }
}
