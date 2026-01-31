package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import feign.codec.Decoder;
import io.github.yvasyliev.deezer.exception.DeezerApiException;
import lombok.RequiredArgsConstructor;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.lang.reflect.Type;

@RequiredArgsConstructor
public class JsonDecoder implements DeezerDecoder {
    private final JsonMapper jsonMapper;

    @Override
    public Object decode(Response response, Type type, Decoder chain)
            throws IOException, JacksonException, DeezerApiException {
        if (!isApplicationJson(response)) {
            return chain.decode(response, type);
        }

        try {
            var body = jsonMapper.readTree(response.body().asInputStream());
            var error = body.path("error");

            if (error.isMissingNode()) {
                return jsonMapper.treeToValue(body, jsonMapper.constructType(type));
            }

            throw jsonMapper.treeToValue(error, DeezerApiException.class);
        } catch (JacksonException e) {
            if (e.getCause() instanceof IOException ex) {
                throw ex;
            }

            throw e;
        }
    }

    private boolean isApplicationJson(Response response) {
        return response.headers().get("content-type").stream().anyMatch(ct -> ct.startsWith("application/json"));
    }
}
