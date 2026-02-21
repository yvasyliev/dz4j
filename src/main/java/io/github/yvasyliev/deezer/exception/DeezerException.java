package io.github.yvasyliev.deezer.exception;

public class DeezerException extends RuntimeException {
    public DeezerException(String message) {
        super(message);
    }

    public DeezerException(Throwable cause) {
        super(cause);
    }

    public DeezerException(String message, Throwable cause) {
        super(message, cause);
    }
}
