package io.github.yvasyliev.deezer.util;

import feign.Param;
import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.databind.deser.PageDeserializerModifier;
import io.github.yvasyliev.deezer.databind.util.ZeroToNullLocalDateConverter;
import io.github.yvasyliev.deezer.factory.InfosRequestFactory;
import io.github.yvasyliev.deezer.feign.QueryExpander;
import io.github.yvasyliev.deezer.feign.StrictExpander;
import io.github.yvasyliev.deezer.feign.decoder.AccessTokenValidator;
import io.github.yvasyliev.deezer.feign.decoder.BodyValidator;
import io.github.yvasyliev.deezer.feign.decoder.DefaultDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.ErrorDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.HeadersValidator;
import io.github.yvasyliev.deezer.feign.decoder.JsonNodeDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.ResponseValidator;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import lombok.experimental.UtilityClass;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.deser.std.StdConvertingDeserializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class DeezerDefaults {
    public final String API_BASE_URL = "https://api.deezer.com";
    public final String OAUTH_BASE_URL = "https://connect.deezer.com";
    public final String UPLOAD_BASE_URL = "https://upload.deezer.com";
    public final CompletableFuture<AccessToken> EMPTY_ACCESS_TOKEN_FUTURE =
            AuthHelper.createAccessTokenFuture(AuthHelper.createAccessToken(null));

    public JsonMapper jsonMapper() {
        return JsonMapper.builder()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .addModule(new SimpleModule("deezer-api")
                        .setDeserializerModifier(new PageDeserializerModifier())
                        .addDeserializer(
                                LocalDate.class,
                                new StdConvertingDeserializer<>(new ZeroToNullLocalDateConverter())
                        )
                )
                .build();
    }

    public List<ResponseValidator> responseValidators() {
        return new ArrayList<>(List.of(
                new HeadersValidator(),
                new BodyValidator(),
                new AccessTokenValidator()
        ));
    }

    public List<JsonNodeDeserializer> jsonNodeDeserializers(JsonMapper jsonMapper) {
        var defaultDeserializer = new DefaultDeserializer(jsonMapper);

        return new ArrayList<>(List.of(
                new ErrorDeserializer(defaultDeserializer),
                defaultDeserializer
        ));
    }

    public Map<Class<? extends Param.Expander>, Param.Expander> expanders(JsonMapper jsonMapper) {
        return new HashMap<>(Map.of(
                QueryExpander.class, new QueryExpander(jsonMapper),
                StrictExpander.class, new StrictExpander()
        ));
    }

    public TokenManager<AccessToken> accessTokenTokenManager(
            Supplier<CompletableFuture<AccessToken>> accessTokenSupplier
    ) {
        return tokenManager(token -> true, accessTokenSupplier, AccessToken::token);
    }

    public TokenManager<Infos> uploadTokenManager(InfosRequestFactory infos) {
        return tokenManager(
                token -> !token.isDone() || !token.isCompletedExceptionally() && !token.join().isUploadTokenExpired(),
                () -> infos.getInfos().executeAsync(),
                Infos::uploadToken
        );
    }

    public <T> Consumer<T> noopConsumer() {
        return t -> {};
    }

    private <T> TokenManager<T> tokenManager(
            Predicate<CompletableFuture<T>> tokenValidator,
            Supplier<CompletableFuture<T>> tokenSupplier,
            Function<T, String> tokenMapper
    ) {
        return new TokenManager<>(tokenValidator, tokenSupplier, tokenMapper);
    }
}
