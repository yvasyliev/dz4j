package io.github.yvasyliev.deezer.feign.codec;

import feign.Response;
import io.github.yvasyliev.deezer.exception.ResponseException;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class HeadersValidatorTest {
    private static final ResponseValidator VALIDATOR = new HeadersValidator();

    @Test
    void shouldThrowResponseExceptionIfContentTypeHeaderIsMissing() {
        @Cleanup var response = Response.builder()
                .request(mock())
                .build();

        assertThrows(ResponseException.class, () -> VALIDATOR.validate(response, Boolean.class));
    }

    @Test
    void shouldPassValidation() {
        @Cleanup var response = Response.builder()
                .request(mock())
                .headers(Map.of("content-type", List.of("application/json")))
                .build();

        VALIDATOR.validate(response, Boolean.class);
    }
}
