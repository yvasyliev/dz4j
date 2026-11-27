package io.github.yvasyliev.dz4j.feign.codec;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import feign.Response;
import io.github.yvasyliev.dz4j.exception.ResponseException;
import java.lang.reflect.Type;
import org.junit.jupiter.api.Test;

class BodyValidatorTest {
    private static final ResponseValidator VALIDATOR = new BodyValidator();

    @Test
    @SuppressWarnings("UnnecessarilyFullyQualified")
    void shouldPassValidation() {
        try (var response = Response.builder()
                .request(mock())
                .body(mock(Response.Body.class))
                .build()) {
            assertDoesNotThrow(() -> VALIDATOR.validate(response, mock()));
        }
    }

    @Test
    @SuppressWarnings("UnnecessarilyFullyQualified")
    void shouldFailValidation() {
        var type = mock(Type.class);

        try (var response = Response.builder().request(mock()).build()) {
            assertThrows(ResponseException.class, () -> VALIDATOR.validate(response, type));
        }
    }
}
