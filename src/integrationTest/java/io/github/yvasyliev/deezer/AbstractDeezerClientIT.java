package io.github.yvasyliev.deezer;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.common.ContentTypes;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import io.github.yvasyliev.deezer.util.DeezerDefaults;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.BeforeEach;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest
abstract class AbstractDeezerClientIT {
    protected static final String ACCESS_TOKEN = "test_access_token";
    private static final JsonMapper MAPPER = DeezerDefaults.jsonMapper();
    private DeezerClient deezerClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        deezerClient = DeezerClient.builder()
                .apiBaseUrl(wmRuntimeInfo.getHttpBaseUrl())
                .authorization(ACCESS_TOKEN)
                .build();
    }

    protected <T> void stubRequest(StubArguments<T> args) throws IOException {
        var body = read(args.file());
        var expected = MAPPER.readValue(body, args.type());
        var builder = args.mappingBuilder().willReturn(aResponse()
                .withStatus(HttpURLConnection.HTTP_OK)
                .withHeader(ContentTypes.CONTENT_TYPE, ContentTypes.APPLICATION_JSON)
                .withBody(body)
        );

        args.pathParams().forEach(builder::withPathParam);
        args.queryParams().forEach(builder::withQueryParam);
        args.formParams().forEach(builder::withFormParam);

        stubFor(builder);

        var request = args.methodFactory().apply(deezerClient);

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

    @Getter
    @Setter
    @Accessors(fluent = true, chain = true)
    protected static class StubArguments<T> {
        private final Map<String, StringValuePattern> pathParams = new HashMap<>();
        private final Map<String, StringValuePattern> queryParams = new HashMap<>();
        private final Map<String, StringValuePattern> formParams = new HashMap<>();
        private MappingBuilder mappingBuilder;
        private String file;
        private Function<DeezerClient, DeezerRequest<T>> methodFactory;
        private TypeReference<T> type;

        public StubArguments<T> pathParam(String key, StringValuePattern value) {
            pathParams.put(key, value);

            return this;
        }

        public StubArguments<T> pathParam(String key, Object value) {
            return pathParam(key, equalTo(String.valueOf(value)));
        }

        public StubArguments<T> queryParam(String key, StringValuePattern value) {
            queryParams.put(key, value);

            return this;
        }

        public StubArguments<T> queryParam(String key, Object value) {
            return queryParam(key, equalTo(String.valueOf(value)));
        }

        public StubArguments<T> formParam(String key, StringValuePattern value) {
            formParams.put(key, value);

            return this;
        }

        public StubArguments<T> formParam(String key, Object value) {
            return formParam(key, equalTo(String.valueOf(value)));
        }
    }
}
