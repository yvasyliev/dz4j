package io.github.yvasyliev.dz4j;

import com.github.tomakehurst.wiremock.common.ContentTypes;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static com.github.tomakehurst.wiremock.client.WireMock.aMultipart;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

class UploadIT extends AbstractIT {
    private static final String UPLOAD_TOKEN = "dyq9m5jxfh2bgh966v2npj2kevcdw6pw";
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .baseUrl(baseUrls -> baseUrls.api(wmRuntimeInfo.getHttpBaseUrl())
                        .upload(wmRuntimeInfo.getHttpBaseUrl())
                )
                .authorization(ACCESS_TOKEN)
                .build();
    }

    @Test
    void shouldUploadPlaylistCoverWithFile() throws IOException {
        var playlistId = 500138701L;
        var content = "fake_cover";
        var cover = createTempFile(content);
        var body = read("/response/playlist/upload-cover.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubUploadPlaylistCover(playlistId, cover.getName(), content, body);

        assertEquals(expected, deezerClient.upload().uploadPlaylistCover(playlistId, cover));
    }

    @Test
    void shouldUploadPlaylistCoverWithBytes() throws IOException {
        var playlistId = 500138701L;
        var cover = "fake_cover";
        var fileName = "fake_cover.jpg";
        var body = read("/response/playlist/upload-cover.json");
        var expected = MAPPER.readValue(body, Boolean.class);

        stubUploadPlaylistCover(playlistId, fileName, cover, body);

        assertEquals(
                expected,
                deezerClient.upload().uploadPlaylistCover(playlistId, cover.getBytes(StandardCharsets.UTF_8), fileName)
        );
    }

    private void stubUploadPlaylistCover(long playlistId, String fileName, String fileContent, String responseBody)
            throws IOException {
        stubFor(get(urlPathEqualTo("/infos"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(read("/response/infos/get-infos.json"))));
        stubFor(post(urlPathTemplate("/playlist/{playlistId}"))
                .withPathParam("playlistId", equalTo(playlistId))
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
                        .withBody(equalTo(fileContent)))
                .willReturn(okJson(responseBody))
        );
    }

    private File createTempFile(String content) throws IOException {
        var path = Files.createTempFile("deezer-upload-it-", ".jpg");
        var file = Files.writeString(path, content).toFile();

        file.deleteOnExit();

        return file;
    }
}
