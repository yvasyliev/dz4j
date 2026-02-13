package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import feign.Util;
import io.github.yvasyliev.deezer.exception.AccessTokenException;
import io.github.yvasyliev.deezer.model.AccessToken;

import java.io.IOException;
import java.lang.reflect.Type;

public class AccessTokenValidator implements ResponseValidator {
    @Override
    public void validate(Response response, Type type) throws AccessTokenException {
        if (AccessToken.class.equals(type) && isTextHtml(response)) {
            throwAccessTokenException(response);
        }
    }

    private boolean isTextHtml(Response response) {
        return response.headers().get("content-type").stream().anyMatch(ct -> ct.startsWith("text/html"));
    }

    private void throwAccessTokenException(Response response) throws AccessTokenException {
        try {
            throw new AccessTokenException(readBody(response), response);
        } catch (IOException suppressed) {
            var e = new AccessTokenException("Invalid access token response", response);

            e.addSuppressed(suppressed);

            throw e;
        }
    }

    private String readBody(Response response) throws IOException {
        return Util.toString(response.body().asReader(response.charset()));
    }
}
