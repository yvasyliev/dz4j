package io.github.yvasyliev.deezer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.core.type.TypeReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.aMultipart;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class UploadIT extends AbstractDeezerClientIT {
    private static final Supplier<Stream<Arguments>> testSuccessfulScenario = () -> {
        var playlistId = 500138701L;
        var fileName = "cover.jpg";
        var cover = "fake_cover_binary".getBytes(StandardCharsets.UTF_8);
        var coverFile = createTempCover(cover);

        return Stream.of(
                arguments("file", new StubArguments<Boolean>()
                        .mappingBuilder(post(urlPathTemplate("/playlist/{playlistId}"))
                                .withMultipartRequestBody(aMultipart()
                                        .withName("access_token")
                                        .withBody(equalTo(ACCESS_TOKEN)))
                                .withMultipartRequestBody(aMultipart()
                                        .withName("upload_token")
                                        .withBody(equalTo(UPLOAD_TOKEN))))
                        .pathParam("playlistId", playlistId)
                        .file("/response/playlist/upload-cover.json")
                        .methodFactory(client -> client.upload().uploadPlaylistCover(playlistId, coverFile))
                        .type(new TypeReference<>() {})
                ),
                arguments("bytes", new StubArguments<Boolean>()
                        .mappingBuilder(post(urlPathTemplate("/playlist/{playlistId}"))
                                .withMultipartRequestBody(aMultipart()
                                        .withName("access_token")
                                        .withBody(equalTo(ACCESS_TOKEN)))
                                .withMultipartRequestBody(aMultipart()
                                        .withName("upload_token")
                                        .withBody(equalTo(UPLOAD_TOKEN))))
                        .pathParam("playlistId", playlistId)
                        .file("/response/playlist/upload-cover.json")
                        .methodFactory(client -> client.upload().uploadPlaylistCover(playlistId, cover, fileName))
                        .type(new TypeReference<>() {})
                )
        );
    };

    @ParameterizedTest(name = "should upload playlist cover from {0}", quoteTextArguments = false)
    @FieldSource
    void testSuccessfulScenario(String name, StubArguments<?> args) throws IOException {
        stubInfos();
        stubRequest(args);
    }

    private void stubInfos() throws IOException {
        stubFor(get(urlPathEqualTo("/infos"))
                .withQueryParam("access_token", equalTo(ACCESS_TOKEN))
                .willReturn(okJson(read("/response/infos/get-infos.json"))));
    }

    private String read(String file) throws IOException {
        try (InputStream inputStream = Objects.requireNonNull(this.getClass().getResourceAsStream(file));
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            inputStream.transferTo(outputStream);

            return outputStream.toString(StandardCharsets.UTF_8);
        }
    }

    private static File createTempCover(byte[] bytes) {
        try {
            var path = Files.createTempFile("deezer-upload-it-", ".jpg");
            Files.write(path, bytes);
            var file = path.toFile();
            file.deleteOnExit();

            return file;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}


