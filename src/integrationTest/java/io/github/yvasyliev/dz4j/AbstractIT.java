package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.github.yvasyliev.dz4j.databind.json.DeezerJsonMapperBuilder;
import io.github.yvasyliev.dz4j.exception.AbstractDeezerApiException;
import io.github.yvasyliev.dz4j.request.DeezerRequest;
import io.github.yvasyliev.dz4j.util.Customizer;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@WireMockTest
abstract class AbstractIT {
    private static final Clock CLOCK = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    @SuppressWarnings("checkstyle:DeclarationOrder")
    protected static final String ACCESS_TOKEN = "test_access_token";

    @SuppressWarnings("checkstyle:DeclarationOrder")
    protected static final JsonMapper MAPPER =
            Customizer.customize(new DeezerJsonMapperBuilder(), AbstractIT::testJsonMapper).build();

    protected static void testJsonMapper(DeezerJsonMapperBuilder builder) {
        builder.handlerInstantiator(hi -> hi.expiresConverter(converter -> converter.clock(CLOCK)));
    }

    protected <T> void assertEquals(T expected, DeezerRequest<T> request) {
        Assertions.assertEquals(expected, request.execute());
        Assertions.assertEquals(expected, request.executeAsync().join());
    }

    protected void assertThrows(AbstractDeezerApiException expected, DeezerRequest<?> request) {
        assertThatThrownBy(request::execute)
                .isInstanceOf(AbstractDeezerApiException.class)
                .usingRecursiveComparison()
                .isEqualTo(expected);

        assertThat(request.executeAsync())
                .failsWithin(Duration.ofSeconds(1))
                .withThrowableThat()
                .withCauseInstanceOf(AbstractDeezerApiException.class)
                .havingCause()
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
