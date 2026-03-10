package io.github.yvasyliev.deezer.feign.decoder;

import feign.Response;
import io.github.yvasyliev.deezer.exception.AccessTokenResponseException;
import io.github.yvasyliev.deezer.model.AccessToken;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessTokenValidatorTest {
    private static final ResponseValidator VALIDATOR = new AccessTokenValidator();

    @Test
    void shouldPassValidationIfTypeIsNotAccessToken() {
        @Cleanup var response = mock(Response.class);

        assertDoesNotThrow(() -> VALIDATOR.validate(response, Boolean.class));
    }

    @Test
    void shouldPassValidationIfContentTypeIsNotTextHtml() {
        @Cleanup var response = Response.builder()
                .request(mock())
                .headers(Map.of("content-type", List.of("application/json")))
                .build();

        assertDoesNotThrow(() -> VALIDATOR.validate(response, AccessToken.class));
    }

    @Test
    void shouldFailValidationNormally() {
        @Cleanup var response = Response.builder()
                .request(mock())
                .headers(Map.of("content-type", List.of("text/html; charset=UTF-8")))
                .body("<html><body>Invalid token</body></html>", StandardCharsets.UTF_8)
                .build();

        assertThrows(AccessTokenResponseException.class, () -> VALIDATOR.validate(response, AccessToken.class));
    }

    @Test
    void shouldFailValidationWithSuppressedException() throws IOException {
        @Cleanup var body = mock(Response.Body.class);
        @Cleanup var response = Response.builder()
                .request(mock())
                .headers(Map.of("content-type", List.of("text/html; charset=UTF-8")))
                .body(body)
                .build();

        when(body.asReader(any())).thenThrow(IOException.class);

        var exception = assertThrows(
                AccessTokenResponseException.class,
                () -> VALIDATOR.validate(response, AccessToken.class)
        );

        assertThat(exception.getSuppressed()).anySatisfy(e -> assertThat(e).isInstanceOf(IOException.class));
    }
}
