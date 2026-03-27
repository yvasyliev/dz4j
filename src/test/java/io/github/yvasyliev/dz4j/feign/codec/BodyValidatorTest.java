package io.github.yvasyliev.dz4j.feign.codec;

import feign.Response;
import io.github.yvasyliev.dz4j.exception.ResponseException;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class BodyValidatorTest {
    private static final ResponseValidator VALIDATOR = new BodyValidator();

    @Test
    void shouldPassValidation() {
        @Cleanup var response = Response.builder()
                .request(mock())
                .body(mock(Response.Body.class))
                .build();

        assertDoesNotThrow(() -> VALIDATOR.validate(response, mock()));
    }

    @Test
    void shouldFailValidation() {
        @Cleanup var response = Response.builder()
                .request(mock())
                .build();

        assertThrows(ResponseException.class, () -> VALIDATOR.validate(response, mock()));
    }
}
