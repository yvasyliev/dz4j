package io.github.yvasyliev.dz4j.feign.codec;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import feign.Response;
import io.github.yvasyliev.dz4j.exception.ResponseException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HeadersValidatorTest {
    private static final ResponseValidator VALIDATOR = new HeadersValidator();

    @Test
    @SuppressWarnings("UnnecessarilyFullyQualified")
    void shouldThrowResponseExceptionIfContentTypeHeaderIsMissing() {
        try (var response = Response.builder().request(mock()).build()) {
            assertThrows(ResponseException.class, () -> VALIDATOR.validate(response, Boolean.class));
        }
    }

    @Test
    @SuppressWarnings("UnnecessarilyFullyQualified")
    void shouldPassValidation() {
        try (var response = Response.builder()
                .request(mock())
                .headers(Map.of("content-type", List.of("application/json")))
                .build()) {
            VALIDATOR.validate(response, Boolean.class);
        }
    }
}
