package io.github.yvasyliev.dz4j.feign.codec;

import feign.Response;
import feign.Util;
import io.github.yvasyliev.dz4j.exception.AccessTokenResponseException;
import io.github.yvasyliev.dz4j.model.AccessToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Validates the access token response to ensure it is not an HTML error page.
 */
public class AccessTokenValidator implements ResponseValidator {
    /**
     * Validates the access token response. If the expected type is {@link AccessToken} and the response content type is
     * {@code text/html}, it throws an {@link AccessTokenResponseException} with the response body as the message.
     *
     * @param response {@inheritDoc}
     * @param type     {@inheritDoc}
     * @throws AccessTokenResponseException if the response is an HTML error page instead of a valid access token
     *                                      response
     */
    @Override
    public void validate(Response response, Type type) throws AccessTokenResponseException {
        if (AccessToken.class.equals(type) && isTextHtml(response)) {
            throw buildAccessTokenException(response);
        }
    }

    private boolean isTextHtml(Response response) {
        return response.headers()
                .getOrDefault("content-type", List.of())
                .stream()
                .anyMatch(ct -> ct.startsWith("text/html"));
    }

    private AccessTokenResponseException buildAccessTokenException(Response response) {
        try {
            return new AccessTokenResponseException(readBody(response), response);
        } catch (IOException suppressed) {
            var e = new AccessTokenResponseException("Invalid access token response", response);

            e.addSuppressed(suppressed);

            return e;
        }
    }

    private String readBody(Response response) throws IOException {
        return Util.toString(response.body().asReader(response.charset()));
    }
}
