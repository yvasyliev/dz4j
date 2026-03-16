package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.yvasyliev.deezer.exception.DeezerApiException;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.util.DeezerDefaults;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;
import java.util.concurrent.CompletionException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest
public class ExceptionIT {
    private static final JsonMapper MAPPER = DeezerDefaults.jsonMapper();
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .build();
    }

    @Test
    void shouldThrowDataException() throws IOException {
        var body = read("/response/exception/data-exception.json");
        var expected = readError(body, DeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowException() throws IOException {
        var body = read("/response/exception/exception.json");
        var expected = readError(body, DeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowIndividualAccountChangedNotAllowedException() throws IOException {
        var body = read("/response/exception/individual-acccount-changed-not-allowed-exception.json");
        var expected = readError(body, DeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowInvalidQueryException() throws IOException {
        var body = read("/response/exception/invalid-query-exception.json");
        var expected = readError(body, DeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowMissingParameterException() throws IOException {
        var body = read("/response/exception/missing-parameter-exception.json");
        var expected = readError(body, DeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowOAuthException() throws IOException {
        var body = read("/response/exception/oauth-exception.json");
        var expected = readError(body, DeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowParameterException() throws IOException {
        var body = read("/response/exception/parameter-exception.json");
        var expected = readError(body, DeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowSimpleApiHttpException() throws IOException {
        var body = read("/response/exception/simple-api-http-exception.json");
        var expected = readError(body, DeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(jsonResponse(body, HttpURLConnection.HTTP_NOT_FOUND)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    private <T extends DeezerApiException, R> void assertThrows(T expected, DeezerRequest<R> request) {
        var e1 = Assertions.assertThrows(DeezerApiException.class, request::execute);

        assertThat(e1).usingRecursiveComparison().isEqualTo(expected);

        var e2 = Assertions.assertThrows(CompletionException.class, () -> request.executeAsync().join());

        assertThat(e2).cause().usingRecursiveComparison().isEqualTo(expected);
    }

    private String read(String file) throws IOException {
        @Cleanup var inputStream = Objects.requireNonNull(this.getClass().getResourceAsStream(file));

        return new String(inputStream.readAllBytes());
    }

    private <T> T readError(String json, Class<T> type) {
        return MAPPER.treeToValue(MAPPER.readTree(json).path("error"), type);
    }
}
