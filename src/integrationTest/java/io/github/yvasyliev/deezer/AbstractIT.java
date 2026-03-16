package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.github.yvasyliev.deezer.exception.DeezerApiException;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.util.DeezerDefaults;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@WireMockTest
abstract class AbstractIT {
    protected static final String ACCESS_TOKEN = "test_access_token";
    protected static final Clock CLOCK = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    protected static final JsonMapper MAPPER = DeezerDefaults.jsonMapper(CLOCK);

    protected <T> void assertEquals(T expected, DeezerRequest<T> request) {
        Assertions.assertEquals(expected, request.execute());
        Assertions.assertEquals(expected, request.executeAsync().join());
    }

    protected void assertThrows(DeezerApiException expected, DeezerRequest<?> request) {
        assertThatThrownBy(request::execute)
                .isInstanceOf(DeezerApiException.class)
                .usingRecursiveComparison()
                .isEqualTo(expected);

        assertThatThrownBy(() -> request.executeAsync().join())
                .isInstanceOf(CompletionException.class)
                .hasCauseInstanceOf(DeezerApiException.class)
                .cause()
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    protected StringValuePattern equalTo(Object value) {
        return WireMock.equalTo(String.valueOf(value));
    }

    protected String read(String file) throws IOException {
        @Cleanup var inputStream = Objects.requireNonNull(this.getClass().getResourceAsStream(file));

        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}
