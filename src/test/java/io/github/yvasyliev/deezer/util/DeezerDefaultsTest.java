package io.github.yvasyliev.deezer.util;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.factory.InfosRequestFactory;
import io.github.yvasyliev.deezer.feign.QueryExpander;
import io.github.yvasyliev.deezer.feign.StrictExpander;
import io.github.yvasyliev.deezer.feign.decoder.AccessTokenValidator;
import io.github.yvasyliev.deezer.feign.decoder.BodyValidator;
import io.github.yvasyliev.deezer.feign.decoder.DefaultDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.ErrorDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.HeadersValidator;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import io.github.yvasyliev.deezer.model.Page;
import io.github.yvasyliev.deezer.request.DeezerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.type;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeezerDefaultsTest {
    @Test
    void testJsonMapper() {
        var jsonMapper = DeezerDefaults.jsonMapper(Clock.systemDefaultZone());

        assertNotNull(jsonMapper);
        assertTrue(jsonMapper.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT));
        assertThat(jsonMapper.registeredModules()).anyMatch(module -> "deezer-api".equals(module.getModuleName()));
    }

    @Test
    void testResponseValidators() {
        var expected = List.of(
                new HeadersValidator(),
                new BodyValidator(),
                new AccessTokenValidator()
        );
        var actual = DeezerDefaults.responseValidators();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testJsonNodeDeserializers() {
        var jsonMapper = mock(JsonMapper.class);
        var defaultDeserializer = new DefaultDeserializer(jsonMapper);
        var expected = List.of(
                new ErrorDeserializer(defaultDeserializer),
                defaultDeserializer
        );
        var actual = DeezerDefaults.jsonNodeDeserializers(jsonMapper);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testExpanders() {
        var jsonMapper = mock(JsonMapper.class);
        var expected = Map.of(
                QueryExpander.class, new QueryExpander(jsonMapper),
                StrictExpander.class, new StrictExpander()
        );
        var actual = DeezerDefaults.expanders(jsonMapper);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testNoopConsumer() {
        var obj = mock();

        DeezerDefaults.noopConsumer().accept(obj);

        verifyNoInteractions(obj);
    }

    @Nested
    class JsonMapperTest {
        private static final JsonMapper JSON_MAPPER = DeezerDefaults.jsonMapper(Clock.systemDefaultZone());
        private static final Supplier<Stream<Arguments>> shouldDeserializePage = () -> {
            var json = """
                       {
                        "data": [
                            "Hello, World!"
                        ],
                        "total": 1
                       }
                       """;
            var expected = Page.<String>builder().data("Hello, World!").total(1).build();

            return Stream.of(
                    arguments("null", null),
                    arguments(json, expected)
            );
        };
        private static final Supplier<Stream<Arguments>> shouldDeserializeLocalDate = () -> {
            var json1 = """
                        "0000-00-00"
                        """;
            var json2 = """
                        "2026-02-13"
                        """;

            return Stream.of(
                    arguments(json1, null),
                    arguments(json2, LocalDate.of(2026, 2, 13))
            );
        };
        private static final Supplier<Stream<Arguments>> shouldDeserializeLocalDateTime = () -> {
            var json1 = """
                        "0000-00-00 00:00:00"
                        """;
            var json2 = """
                        "2026-02-13 20:02:13"
                        """;

            return Stream.of(
                    arguments(json1, null),
                    arguments(json2, LocalDateTime.of(2026, 2, 13, 20, 2, 13))
            );
        };

        @Test
        void shouldCreateJsonMapper() {
            assertNotNull(JSON_MAPPER);
            assertTrue(JSON_MAPPER.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT));
            assertThat(JSON_MAPPER.registeredModules()).anyMatch(module -> "deezer-api".equals(module.getModuleName()));
        }

        @ParameterizedTest
        @FieldSource
        void shouldDeserializePage(String json, Page<String> expected) {
            var actual = JSON_MAPPER.readValue(json, new TypeReference<Page<String>>() {});

            assertEquals(expected, actual);
        }

        @ParameterizedTest
        @FieldSource
        void shouldDeserializeLocalDate(String json, LocalDate expected) {
            var actual = JSON_MAPPER.readValue(json, LocalDate.class);

            assertEquals(expected, actual);
        }

        @ParameterizedTest
        @FieldSource
        void shouldDeserializeLocalDateTime(String json, LocalDateTime expected) {
            var actual = JSON_MAPPER.readValue(json, LocalDateTime.class);

            assertEquals(expected, actual);
        }
    }

    @Nested
    class AccessTokenManagerTest {
        private static final Supplier<Stream<Arguments>> shouldReturnTrue = () -> Stream.of(
                arguments((AccessToken) null),
                arguments(new AccessToken("token", Instant.now())),
                arguments(new AccessToken("token", Instant.now().plusSeconds(3600)))
        );
        @Mock private Supplier<CompletableFuture<AccessToken>> accessTokenSupplier;
        private TokenManager<AccessToken> accessTokenTokenManager;

        @BeforeEach
        void setUp() {
            accessTokenTokenManager = DeezerDefaults.accessTokenTokenManager(accessTokenSupplier);
        }

        @ParameterizedTest
        @FieldSource
        void shouldReturnTrue(AccessToken accessToken) {
            var tokenValidator = assertThat(accessTokenTokenManager)
                    .extracting("tokenValidator")
                    .asInstanceOf(type(Predicate.class))
                    .actual();

            @SuppressWarnings("unchecked")
            var actual = tokenValidator.test(CompletableFuture.completedFuture(accessToken));

            assertTrue(actual);
        }

        @Test
        void shouldReturnAccessToken() {
            var expected = new AccessToken("token", Instant.now());

            when(accessTokenSupplier.get()).thenReturn(CompletableFuture.completedFuture(expected));

            var tokenSupplier = assertThat(accessTokenTokenManager)
                    .extracting("tokenSupplier")
                    .asInstanceOf(type(Supplier.class))
                    .actual();
            var actual = tokenSupplier.get();

            assertThat(actual).asInstanceOf(type(CompletableFuture.class))
                    .satisfies(future -> assertEquals(expected, future.join()));
        }

        @Test
        void shouldMapToken() {
            var tokenMapper = assertThat(accessTokenTokenManager)
                    .extracting("tokenMapper")
                    .asInstanceOf(type(Function.class))
                    .actual();
            var expected = "token";

            @SuppressWarnings("unchecked")
            var actual = tokenMapper.apply(new AccessToken("token", Instant.now()));

            assertEquals(expected, actual);
        }
    }

    @Nested
    class UploadTokenManagerTest {
        private static final Supplier<Stream<Arguments>> testValidateToken = () -> {
            var future1 = Mockito.<CompletableFuture<Infos>>mock();
            var future2 = CompletableFuture.completedFuture(Infos.builder()
                    .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                    .build()
            );
            var future3 = CompletableFuture.failedFuture(mock());
            var future4 = CompletableFuture.completedFuture(Infos.builder()
                    .uploadTokenExpiresAt(Instant.now().minusSeconds(3600))
                    .build()
            );

            return Stream.of(
                    arguments(future1, true),
                    arguments(future2, true),
                    arguments(future3, false),
                    arguments(future4, false)
            );
        };
        @Mock private InfosRequestFactory infos;
        private TokenManager<Infos> uploadTokenManager;

        @BeforeEach
        void setUp() {
            uploadTokenManager = DeezerDefaults.uploadTokenManager(infos);
        }

        @ParameterizedTest
        @FieldSource
        void testValidateToken(CompletableFuture<Infos> future, boolean expected) {
            var tokenValidator = assertThat(uploadTokenManager)
                    .extracting("tokenValidator")
                    .asInstanceOf(type(Predicate.class))
                    .actual();

            @SuppressWarnings("unchecked")
            var actual = tokenValidator.test(future);

            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnInfos() {
            var request = Mockito.<DeezerRequest<Infos>>mock();
            var expected = Infos.builder().uploadToken("token").build();

            when(infos.getInfos()).thenReturn(request);
            when(request.executeAsync()).thenReturn(CompletableFuture.completedFuture(expected));

            var tokenSupplier = assertThat(uploadTokenManager)
                    .extracting("tokenSupplier")
                    .asInstanceOf(type(Supplier.class))
                    .actual();
            var actual = tokenSupplier.get();

            assertThat(actual).asInstanceOf(type(CompletableFuture.class))
                    .satisfies(future -> assertEquals(expected, future.join()));
        }

        @Test
        void shouldMapToken() {
            var expected = "token";
            var tokenMapper = assertThat(uploadTokenManager)
                    .extracting("tokenMapper")
                    .asInstanceOf(type(Function.class))
                    .actual();

            @SuppressWarnings("unchecked")
            var actual = tokenMapper.apply(Infos.builder().uploadToken(expected).build());

            assertEquals(expected, actual);
        }
    }
}
