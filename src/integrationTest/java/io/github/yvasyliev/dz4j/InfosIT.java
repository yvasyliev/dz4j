package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import io.github.yvasyliev.dz4j.model.Infos;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

class InfosIT extends AbstractIT {
    @Test
    void shouldReturnInfos(WireMockRuntimeInfo wmRuntimeInfo) throws IOException {
        var body = read("/response/infos/get-infos.json");
        var expected = MAPPER.readValue(body, Infos.class);
        var deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .config(config -> config.jsonMapper(AbstractIT::testJsonMapper))
                .build();

        stubFor(get("/infos").willReturn(okJson(body)));

        assertEquals(expected, deezerClient.infos().getInfos());
    }

    @Test
    void shouldReturnInfosWithAccessToken(WireMockRuntimeInfo wmRuntimeInfo) throws IOException {
        var body = read("/response/infos/get-infos.json");
        var expected = MAPPER.readValue(body, Infos.class);
        var deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl()))
                .config(config -> config.jsonMapper(AbstractIT::testJsonMapper))
                .authorization(ACCESS_TOKEN)
                .build();

        stubFor(get(urlPathEqualTo("/infos"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.infos().getInfos());
    }
}
