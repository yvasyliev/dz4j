package io.github.yvasyliev.deezer;

import feign.AsyncFeign;
import feign.form.FormEncoder;
import io.github.yvasyliev.deezer.authorization.AccessTokenProvider;
import io.github.yvasyliev.deezer.authorization.AccessTokenSupplier;
import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.databind.util.ZeroToNullLocalDateConverter;
import io.github.yvasyliev.deezer.factory.AlbumRequestFactory;
import io.github.yvasyliev.deezer.factory.ArtistRequestFactory;
import io.github.yvasyliev.deezer.factory.ChartRequestFactory;
import io.github.yvasyliev.deezer.factory.EditorialRequestFactory;
import io.github.yvasyliev.deezer.factory.EpisodeRequestFactory;
import io.github.yvasyliev.deezer.factory.GenreRequestFactory;
import io.github.yvasyliev.deezer.factory.InfosRequestFactory;
import io.github.yvasyliev.deezer.factory.OAuthRequestFactory;
import io.github.yvasyliev.deezer.factory.OEmbedRequestFactory;
import io.github.yvasyliev.deezer.factory.OptionsRequestFactory;
import io.github.yvasyliev.deezer.factory.PlaylistRequestFactory;
import io.github.yvasyliev.deezer.factory.PodcastRequestFactory;
import io.github.yvasyliev.deezer.factory.RadioRequestFactory;
import io.github.yvasyliev.deezer.factory.SearchRequestFactory;
import io.github.yvasyliev.deezer.factory.TrackRequestFactory;
import io.github.yvasyliev.deezer.factory.UploadRequestFactory;
import io.github.yvasyliev.deezer.factory.UserRequestFactory;
import io.github.yvasyliev.deezer.feign.DeezerContract;
import io.github.yvasyliev.deezer.feign.QueryExpander;
import io.github.yvasyliev.deezer.feign.StrictExpander;
import io.github.yvasyliev.deezer.feign.decoder.AccessTokenValidator;
import io.github.yvasyliev.deezer.feign.decoder.BodyValidator;
import io.github.yvasyliev.deezer.feign.decoder.DeezerDecoder;
import io.github.yvasyliev.deezer.feign.decoder.DefaultDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.ErrorDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.HeadersValidator;
import io.github.yvasyliev.deezer.feign.decoder.OptionalDeserializer;
import io.github.yvasyliev.deezer.feign.decoder.StatusValidator;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import io.github.yvasyliev.deezer.service.AlbumService;
import io.github.yvasyliev.deezer.service.ArtistService;
import io.github.yvasyliev.deezer.service.ChartService;
import io.github.yvasyliev.deezer.service.EditorialService;
import io.github.yvasyliev.deezer.service.EpisodeService;
import io.github.yvasyliev.deezer.service.GenreService;
import io.github.yvasyliev.deezer.service.InfosService;
import io.github.yvasyliev.deezer.service.OAuthService;
import io.github.yvasyliev.deezer.service.OEmbedService;
import io.github.yvasyliev.deezer.service.OptionsService;
import io.github.yvasyliev.deezer.service.PlaylistService;
import io.github.yvasyliev.deezer.service.PodcastService;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.service.SearchService;
import io.github.yvasyliev.deezer.service.TrackService;
import io.github.yvasyliev.deezer.service.UploadService;
import io.github.yvasyliev.deezer.service.UserService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.deser.std.StdConvertingDeserializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Accessors(fluent = true)
public class DeezerClient {
    @Getter(AccessLevel.NONE)
    private final AccessTokenSupplier accessTokenSupplier;

    private final AlbumRequestFactory album;
    private final ArtistRequestFactory artist;
    private final ChartRequestFactory chart;
    private final EditorialRequestFactory editorial;
    private final EpisodeRequestFactory episode;
    private final GenreRequestFactory genre;
    private final InfosRequestFactory infos;
    private final OAuthRequestFactory oauth;
    private final OEmbedRequestFactory oEmbed;
    private final OptionsRequestFactory options;
    private final PlaylistRequestFactory playlist;
    private final PodcastRequestFactory podcast;
    private final RadioRequestFactory radio;
    private final SearchRequestFactory search;
    private final TrackRequestFactory track;
    private final UploadRequestFactory upload;
    private final UserRequestFactory user;

    public DeezerClient() {
        this((Function<OAuthRequestFactory, CompletableFuture<AccessToken>>) null);
    }

    public DeezerClient(@NonNull String accessToken) {
        this(createAccessToken(accessToken));
    }

    public DeezerClient(@NonNull AccessToken accessToken) {
        this(createAccessTokenProviderFactory(accessToken));
    }

    public DeezerClient(int appId, @NonNull String secret, @NonNull String code) {
        this(createAccessTokenProviderFactory(appId, secret, code));
    }

    private DeezerClient(Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenProviderFactory) {
        this(null, null, null, null, null, null, null, accessTokenProviderFactory);
    }

    @Builder
    private DeezerClient(
            JsonMapper jsonMapper,
            Consumer<DeezerDecoder.DeezerDecoderBuilder> decoder,
            Consumer<DeezerContract.DeezerContractBuilder> contract,
            Consumer<AsyncFeign.AsyncBuilder<Object>> feign,
            String apiBasePath,
            String uploadBasePath,
            String oauthBasePath,
            Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenProviderFactory
    ) {
        var builder = createFeignBuilder(jsonMapper, decoder, contract, feign);
        var accessTokenProvider = defaultValue(
                accessTokenProviderFactory,
                () -> createAccessTokenProviderFactory(createAccessToken(null))
        );
        var factory = new RequestFactoryFactory(builder, defaultValue(apiBasePath, "https://api.deezer.com"));

        album = factory.create(AlbumService.class, AlbumRequestFactory::new);
        artist = factory.create(ArtistService.class, ArtistRequestFactory::new);
        oauth = factory.create(
                OAuthService.class,
                OAuthRequestFactory::new,
                defaultValue(oauthBasePath, "https://connect.deezer.com")
        );
        chart = factory.create(ChartService.class, ChartRequestFactory::new);
        editorial = factory.create(EditorialService.class, EditorialRequestFactory::new);
        genre = factory.create(GenreService.class, GenreRequestFactory::new);
        oEmbed = factory.create(OEmbedService.class, OEmbedRequestFactory::new);
        options = factory.create(OptionsService.class, OptionsRequestFactory::new);
        radio = factory.create(RadioService.class, RadioRequestFactory::new);
        search = factory.create(SearchService.class, SearchRequestFactory::new);

        accessTokenSupplier = new AccessTokenSupplier(() -> accessTokenProvider.apply(oauth));

        var accessTokenManager = new TokenManager<>(token -> true, accessTokenSupplier, AccessToken::token);

        episode = factory.create(EpisodeService.class, accessTokenManager, EpisodeRequestFactory::new);
        infos = factory.create(InfosService.class, accessTokenManager, InfosRequestFactory::new);
        playlist = factory.create(PlaylistService.class, accessTokenManager, PlaylistRequestFactory::new);
        podcast = factory.create(PodcastService.class, accessTokenManager, PodcastRequestFactory::new);
        track = factory.create(TrackService.class, accessTokenManager, TrackRequestFactory::new);
        user = factory.create(UserService.class, accessTokenManager, UserRequestFactory::new);

        var uploadTokenManager = new TokenManager<>(
                token -> !token.isDone() || !token.isCompletedExceptionally() && !token.join().isUploadTokenExpired(),
                () -> infos.getInfos().executeAsync(),
                Infos::uploadToken
        );

        upload = factory.create(
                UploadService.class,
                target -> new UploadRequestFactory(target, accessTokenManager, uploadTokenManager),
                defaultValue(uploadBasePath, "https://upload.deezer.com")
        );
    }

    private static AsyncFeign.AsyncBuilder<Object> createFeignBuilder(
            JsonMapper jsonMapper,
            Consumer<DeezerDecoder.DeezerDecoderBuilder> decoderCustomizer,
            Consumer<DeezerContract.DeezerContractBuilder> contractCustomizer,
            Consumer<AsyncFeign.AsyncBuilder<Object>> feignCustomizer
    ) {
        var mapper = Objects.requireNonNullElseGet(
                jsonMapper,
                () -> JsonMapper.builder()
                        .addModule(new SimpleModule().addDeserializer(
                                LocalDate.class,
                                new StdConvertingDeserializer<>(new ZeroToNullLocalDateConverter()))
                        )
                        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                        .build()
        );

        var decoder = DeezerDecoder.builder()
                .responseValidators(new ArrayList<>(List.of(
                        new StatusValidator(),
                        new HeadersValidator(),
                        new BodyValidator(),
                        new AccessTokenValidator()
                )))
                .jsonNodeDeserializers(new ArrayList<>(List.of(
                        new ErrorDeserializer(mapper),
                        new OptionalDeserializer(mapper),
                        new DefaultDeserializer(mapper)
                )))
                .jsonMapper(mapper);
        var contract = DeezerContract.builder().expanders(new HashMap<>(Map.of(
                QueryExpander.class, new QueryExpander(mapper),
                StrictExpander.class, new StrictExpander()
        )));

        defaultValue(decoderCustomizer).accept(decoder);
        defaultValue(contractCustomizer).accept(contract);

        var builder = AsyncFeign.builder()
                .encoder(new FormEncoder())
                .decoder(decoder.build())
                .contract(contract.build());

        defaultValue(feignCustomizer).accept(builder);

        return builder;
    }

    private static <T> T defaultValue(T value, T defaultValue) {
        return Objects.requireNonNullElse(value, defaultValue);
    }

    private static <T> T defaultValue(T value, Supplier<T> defaultSupplier) {
        return Objects.requireNonNullElseGet(value, defaultSupplier);
    }

    private static <T> Consumer<T> defaultValue(Consumer<T> customizer) {
        return Objects.requireNonNullElseGet(customizer, () -> t -> {});
    }

    private static AccessToken createAccessToken(String token) {
        return new AccessToken(token, Instant.MAX);
    }

    private static Function<OAuthRequestFactory, CompletableFuture<AccessToken>> createAccessTokenProviderFactory(
            AccessToken accessToken
    ) {
        return oauth -> CompletableFuture.completedFuture(accessToken);
    }

    private static Function<OAuthRequestFactory, CompletableFuture<AccessToken>> createAccessTokenProviderFactory(
            int appId,
            @NonNull String secret,
            @NonNull String code
    ) {
        return oauth -> oauth.getAccessToken(appId, secret, code).executeAsync();
    }

    public void authorization(String accessToken) {
        authorization(createAccessToken(accessToken));
    }

    public void authorization(@NonNull AccessToken accessToken) {
        var accessTokenProvider = createAccessTokenProviderFactory(accessToken);
        authorization(() -> accessTokenProvider.apply(oauth));
    }

    public void authorization(int appId, @NonNull String secret, String code) {
        var accessTokenProvider = createAccessTokenProviderFactory(appId, secret, code);
        authorization(() -> accessTokenProvider.apply(oauth));
    }

    private void authorization(AccessTokenProvider accessTokenProvider) {
        accessTokenSupplier.setAccessTokenProvider(accessTokenProvider);
    }

    @RequiredArgsConstructor
    private static class RequestFactoryFactory {
        private final AsyncFeign.AsyncBuilder<Object> builder;
        private final String defaultBasePath;

        <T, R> R create(Class<T> target, Function<T, R> factory) {
            return create(target, factory, defaultBasePath);
        }

        <T, R> R create(
                Class<T> target,
                TokenManager<AccessToken> accessTokenManager,
                BiFunction<T, TokenManager<AccessToken>, R> factory
        ) {
            return create(target, t -> factory.apply(t, accessTokenManager));
        }

        <T, R> R create(Class<T> target, Function<T, R> factory, String basePath) {
            return factory.apply(builder.target(target, basePath));
        }
    }

    public static class DeezerClientBuilder {
        private Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenProviderFactory;

        public DeezerClientBuilder authorization(String accessToken) {
            return authorization(createAccessToken(accessToken));
        }

        public DeezerClientBuilder authorization(@NonNull AccessToken accessToken) {
            return accessTokenProviderFactory(createAccessTokenProviderFactory(accessToken));
        }

        public DeezerClientBuilder authorization(int appId, @NonNull String secret, String code) {
            return accessTokenProviderFactory(createAccessTokenProviderFactory(appId, secret, code));
        }

        private DeezerClientBuilder accessTokenProviderFactory(
                Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenProviderFactory
        ) {
            this.accessTokenProviderFactory = accessTokenProviderFactory;
            return this;
        }
    }
}
