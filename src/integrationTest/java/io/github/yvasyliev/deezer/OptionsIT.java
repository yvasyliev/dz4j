package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.deezer.model.Options;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

class OptionsIT extends AbstractIT {
    @Test
    void shouldReturnOptions(WireMockRuntimeInfo wmRuntimeInfo) throws IOException {
        var body = read("/response/options/get-options.json");
        var expected = MAPPER.readValue(body, Options.class);
        var deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .authorization(ACCESS_TOKEN)
                .build();

        stubFor(get(urlPathEqualTo("/options")).willReturn(okJson(body)));

        assertEquals(expected, deezerClient.options().getOptions());
    }
}
