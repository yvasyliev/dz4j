package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import feign.Util;
import feign.codec.StringDecoder;
import io.github.yvasyliev.deezer.exception.AccessTokenResponseException;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.io.IOException;
import java.lang.reflect.Type;

public class AccessTokenFilter extends StringDecoder implements DecoderFilter {
    @Override
    public void filter(Response response, Type type) throws IOException {
        if (AccessToken.class.equals(type) && isTextHtml(response)) {
            var body = response.body();
            var message = body != null
                    ? Util.toString(body.asReader(response.charset()))
                    : "Invalid access token response";

            throw new AccessTokenResponseException(message, response);
        }
    }

    private boolean isTextHtml(Response response) {
        return response.headers().get("content-type").stream().anyMatch(ct -> ct.startsWith("text/html"));
    }
}
