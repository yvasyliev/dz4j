package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class StatusValidatorTest {
    private static final ResponseValidator VALIDATOR = new StatusValidator();

    @Test
    void shouldThrowResponseExceptionIfStatusIsNotOk() {
        @Cleanup var response = Response.builder()
                .request(mock())
                .status(HttpURLConnection.HTTP_BAD_REQUEST)
                .build();

        assertThrows(ResponseException.class, () -> VALIDATOR.validate(response, Boolean.class));
    }

    @Test
    void shouldPassValidation() {
        @Cleanup var response = Response.builder()
                .request(mock())
                .status(HttpURLConnection.HTTP_OK)
                .build();

        assertDoesNotThrow(() -> VALIDATOR.validate(response, Boolean.class));
    }
}
