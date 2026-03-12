package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.common.ContentTypes;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.yvasyliev.deezer.databind.deser.PageDeserializerModifier;
import io.github.yvasyliev.deezer.databind.util.ZeroToNullLocalDateConverter;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeEach;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.deser.std.StdConvertingDeserializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest
abstract class AbstractDeezerClientIT {
    private static final JsonMapper MAPPER = JsonMapper.builder()
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .addModule(new SimpleModule("deezer-api")
                    .setDeserializerModifier(new PageDeserializerModifier())
                    .addDeserializer(
                            LocalDate.class,
                            new StdConvertingDeserializer<>(new ZeroToNullLocalDateConverter())
                    )
            )
            .build();
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBasePath(wmRuntimeInfo.getHttpBaseUrl())
                .build();
    }

    public <T> void stubGet(StubArguments<T> args) throws IOException {
        var body = read(args.file);
        var expected = MAPPER.readValue(body, args.type);
        var builder = get(urlPathTemplate(args.pathTemplate)).willReturn(aResponse()
                .withStatus(HttpURLConnection.HTTP_OK)
                .withHeader(ContentTypes.CONTENT_TYPE, ContentTypes.APPLICATION_JSON)
                .withBody(body)
        );

        args.pathParams.forEach((key, value) -> builder.withPathParam(key, equalTo(value.toString())));
        args.queryParams.forEach((key, value) -> builder.withQueryParam(key, equalTo(value.toString())));

        stubFor(builder);

        var request = args.methodFactory.apply(deezerClient);

        assertEquals(expected, request.execute());
        assertEquals(expected, request.executeAsync().join());
    }

    private byte[] read(String file) throws IOException {
        @Cleanup var inputStream = open(file);

        return inputStream.readAllBytes();
    }

    private InputStream open(String file) {
        return Objects.requireNonNull(this.getClass().getResourceAsStream(file));
    }

    protected record StubArguments<T>(
            String pathTemplate,
            Map<String, Object> pathParams,
            Map<String, Object> queryParams,
            String file,
            Function<DeezerClient, DeezerRequest<T>> methodFactory,
            TypeReference<T> type
    ) {}
}
