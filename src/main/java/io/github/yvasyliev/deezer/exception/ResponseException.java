package io.github.yvasyliev.deezer.exception;

import feign.Response;
import lombok.Getter;

@Getter
public class ResponseException extends DeezerException {
    private final Response response;

    public ResponseException(String message, Response response) {
        super(message);
        this.response = response;
    }
}
