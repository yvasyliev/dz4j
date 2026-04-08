package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.exception.AccessTokenResponseException;
import io.github.yvasyliev.dz4j.model.AccessToken;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.common.ContentTypes.CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class OAuthIT extends AbstractIT {
    @Test
    void shouldReturnAccessToken(WireMockRuntimeInfo wmRuntimeInfo) throws IOException {
        var appId = 123;
        var secret = "secret";
        var code = "code";
        var body = read("/response/oauth/get-access-token.json");
        var expected = MAPPER.readValue(body, AccessToken.class);
        var deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.oauth(wmRuntimeInfo.getHttpBaseUrl()))
                .build();

        stubFor(get(urlPathEqualTo("/oauth/access_token.php"))
                .withQueryParam("app_id", equalTo(String.valueOf(appId)))
                .withQueryParam("secret", equalTo(secret))
                .withQueryParam("code", equalTo(code))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.oauth().getAccessToken(appId, secret, code));
    }

    @Test
    void shouldThrowAccessTokenException(WireMockRuntimeInfo wmRuntimeInfo) throws IOException {
        var appId = 123;
        var secret = "secret";
        var code = "code";
        var expected = read("/response/oauth/wrong-code.html");
        var request = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.oauth(wmRuntimeInfo.getHttpBaseUrl()))
                .build()
                .oauth()
                .getAccessToken(appId, secret, code);

        stubFor(get(urlPathEqualTo("/oauth/access_token.php"))
                .withQueryParam("app_id", equalTo(String.valueOf(appId)))
                .withQueryParam("secret", equalTo(secret))
                .withQueryParam("code", equalTo(code))
                .willReturn(ok(expected).withHeader(CONTENT_TYPE, "text/html; charset=utf-8"))
        );

        assertThatThrownBy(request::execute)
                .isInstanceOf(AccessTokenResponseException.class)
                .hasMessage(expected);

        assertThat(request.executeAsync())
                .failsWithin(Duration.ofSeconds(1))
                .withThrowableThat()
                .withCause(new AccessTokenResponseException(expected, mock()));
    }
}
