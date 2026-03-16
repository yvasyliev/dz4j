package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.common.ContentTypes;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.util.DeezerDefaults;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.aMultipart;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

@WireMockTest
class UploadIT {
    private static final String ACCESS_TOKEN = "test_access_token";
    private static final String UPLOAD_TOKEN = "dyq9m5jxfh2bgh966v2npj2kevcdw6pw";
    private static final JsonMapper MAPPER = DeezerDefaults.jsonMapper();
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .uploadBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .authorization(ACCESS_TOKEN)
                .build();
    }

    @Test
    void shouldUploadPlaylistCoverWithFile() throws IOException {
        var playlistId = 500138701L;
        var cover = createTempFile("fake_cover");
        var body = read("/response/playlist/upload-cover.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubInfos();
        stubFor(post(urlPathTemplate("/playlist/{playlistId}"))
                .withPathParam("playlistId", equalTo(String.valueOf(playlistId)))
                .withMultipartRequestBody(aMultipart()
                        .withName("access_token")
                        .withBody(equalTo(ACCESS_TOKEN)))
                .withMultipartRequestBody(aMultipart()
                        .withName("upload_token")
                        .withBody(equalTo(UPLOAD_TOKEN)))
                .withMultipartRequestBody(aMultipart()
                        .withHeader(ContentTypes.CONTENT_TYPE, equalTo("image/jpeg"))
                        .withName("file")
                        .withFileName(cover.getName())
                        .withBody(equalTo("fake_cover")))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.upload().uploadPlaylistCover(playlistId, cover));
    }

    @Test
    void shouldUploadPlaylistCoverWithoutBytes() throws IOException {
        var playlistId = 500138701L;
        var cover = "fake_cover";
        var fileName = "fake_cover.jpg";
        var body = read("/response/playlist/upload-cover.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubInfos();
        stubFor(post(urlPathTemplate("/playlist/{playlistId}"))
                .withPathParam("playlistId", equalTo(String.valueOf(playlistId)))
                .withMultipartRequestBody(aMultipart()
                        .withName("access_token")
                        .withBody(equalTo(ACCESS_TOKEN)))
                .withMultipartRequestBody(aMultipart()
                        .withName("upload_token")
                        .withBody(equalTo(UPLOAD_TOKEN)))
                .withMultipartRequestBody(aMultipart()
                        .withHeader(ContentTypes.CONTENT_TYPE, equalTo("image/jpeg"))
                        .withName("file")
                        .withFileName(fileName)
                        .withBody(equalTo(cover)))
                .willReturn(okJson(body))
        );

        assertEquals(expected, deezerClient.upload().uploadPlaylistCover(playlistId, cover.getBytes(), fileName));
    }

    private void stubInfos() throws IOException {
        stubFor(get(urlPathEqualTo("/infos"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(read("/response/infos/get-infos.json"))));
    }

    private <T> void assertEquals(T expected, DeezerRequest<T> request) {
        Assertions.assertEquals(expected, request.execute());
        Assertions.assertEquals(expected, request.executeAsync().join());
    }

    private String read(String file) throws IOException {
        @Cleanup var inputStream = Objects.requireNonNull(this.getClass().getResourceAsStream(file));

        return new String(inputStream.readAllBytes());
    }

    private static File createTempFile(String content) throws IOException {
        var path = Files.createTempFile("deezer-upload-it-", ".jpg");

        Files.write(path, content.getBytes());

        var file = path.toFile();

        file.deleteOnExit();

        return file;
    }
}
