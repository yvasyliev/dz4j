package io.github.yvasyliev.dz4j.exception;

/**
 * Base exception class for all exceptions related to Dz4j.
 */
public class DeezerException extends RuntimeException {
    /**
     * Constructs a new {@link DeezerException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public DeezerException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link DeezerException} with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public DeezerException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new {@link DeezerException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause   the cause of the exception
     */
    public DeezerException(String message, Throwable cause) {
        super(message, cause);
    }
}
