package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.exception.AbstractDeezerApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

class ExceptionIT extends AbstractIT {
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .build();
    }

    @Test
    void shouldThrowDataException() throws IOException {
        var body = read("/response/exception/data-exception.json");
        var expected = readError(body, AbstractDeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowException() throws IOException {
        var body = read("/response/exception/exception.json");
        var expected = readError(body, AbstractDeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowIndividualAccountChangedNotAllowedException() throws IOException {
        var body = read("/response/exception/individual-acccount-changed-not-allowed-exception.json");
        var expected = readError(body, AbstractDeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowInvalidQueryException() throws IOException {
        var body = read("/response/exception/invalid-query-exception.json");
        var expected = readError(body, AbstractDeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowMissingParameterException() throws IOException {
        var body = read("/response/exception/missing-parameter-exception.json");
        var expected = readError(body, AbstractDeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowOAuthException() throws IOException {
        var body = read("/response/exception/oauth-exception.json");
        var expected = readError(body, AbstractDeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowParameterException() throws IOException {
        var body = read("/response/exception/parameter-exception.json");
        var expected = readError(body, AbstractDeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowSimpleApiHttpException() throws IOException {
        var body = read("/response/exception/simple-api-http-exception.json");
        var expected = readError(body, AbstractDeezerApiException.class);

        stubFor(get(urlEqualTo("/options")).willReturn(jsonResponse(body, HttpURLConnection.HTTP_NOT_FOUND)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    private <T> T readError(String json, Class<T> type) {
        return MAPPER.treeToValue(MAPPER.readTree(json).path("error"), type);
    }
}
