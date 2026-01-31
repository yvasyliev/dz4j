package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import io.github.yvasyliev.deezer.exception.InvalidResponseStatusException;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

public class StatusFilter implements DecoderFilter {
    @Override
    public void filter(Response response, Type type) throws InvalidResponseStatusException {
        if (response.status() != HttpURLConnection.HTTP_OK) {
            throw new InvalidResponseStatusException(response);
        }
    }
}
