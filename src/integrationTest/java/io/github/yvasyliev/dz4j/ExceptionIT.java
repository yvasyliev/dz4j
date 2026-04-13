package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.exception.AbstractDeezerApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "should throw {0}Exception")
    @CsvSource(textBlock = """
                           Data, data-exception.json
                           '', exception.json
                           IndividualAccountChangedNotAllowed, individual-account-changed-not-allowed-exception.json
                           InvalidQuery, invalid-query-exception.json
                           MissingParameter, missing-parameter-exception.json
                           OAuth, oauth-exception.json
                           Parameter, parameter-exception.json
                           SimpleApiHttp, simple-api-http-exception.json
                           """)
    void shouldThrowDeezerApiException(String name, String json) throws IOException {
        var body = read("/response/exception/" + json);
        var expected = readError(body);

        stubFor(get(urlEqualTo("/options")).willReturn(okJson(body)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    @Test
    void shouldThrowSimpleApiHttpException() throws IOException {
        var body = read("/response/exception/simple-api-http-exception.json");
        var expected = readError(body);

        stubFor(get(urlEqualTo("/options")).willReturn(jsonResponse(body, HttpURLConnection.HTTP_NOT_FOUND)));

        assertThrows(expected, deezerClient.options().getOptions());
    }

    private AbstractDeezerApiException readError(String json) {
        return MAPPER.treeToValue(MAPPER.readTree(json).path("error"), AbstractDeezerApiException.class);
    }
}
