package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import io.github.yvasyliev.deezer.exception.NoContentTypeResponseHeaderException;
import io.github.yvasyliev.deezer.exception.NoResponseHeadersException;

import java.lang.reflect.Type;

public class HeadersFilter implements DecoderFilter {
    @Override
    public void filter(Response response, Type type)
            throws NoResponseHeadersException, NoContentTypeResponseHeaderException {
        var headers = response.headers();

        if (headers == null) {
            throw new NoResponseHeadersException(response);
        }

        if (!headers.containsKey("content-type")) {
            throw new NoContentTypeResponseHeaderException(response);
        }
    }
}
