package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import feign.Util;
import io.github.yvasyliev.deezer.exception.AccessTokenResponseException;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.io.IOException;
import java.lang.reflect.Type;

public class AccessTokenValidator implements ResponseValidator {
    @Override
    public void validate(Response response, Type type) throws AccessTokenResponseException {
        if (AccessToken.class.equals(type) && isTextHtml(response)) {
            throwAccessTokenException(response);
        }
    }

    private boolean isTextHtml(Response response) {
        return response.headers().get("content-type").stream().anyMatch(ct -> ct.startsWith("text/html"));
    }

    private void throwAccessTokenException(Response response) throws AccessTokenResponseException {
        try {
            throw new AccessTokenResponseException(readBody(response), response);
        } catch (IOException suppressed) {
            var e = new AccessTokenResponseException("Invalid access token response", response);

            e.addSuppressed(suppressed);

            throw e;
        }
    }

    private String readBody(Response response) throws IOException {
        return Util.toString(response.body().asReader(response.charset()));
    }
}
