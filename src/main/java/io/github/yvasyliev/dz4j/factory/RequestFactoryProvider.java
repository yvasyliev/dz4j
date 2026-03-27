package io.github.yvasyliev.dz4j.factory;

import feign.AsyncFeign;
import io.github.yvasyliev.dz4j.authorization.TokenManager;
import io.github.yvasyliev.dz4j.configuration.BaseUrls;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.service.AlbumService;
import io.github.yvasyliev.dz4j.service.ArtistService;
import io.github.yvasyliev.dz4j.service.ChartService;
import io.github.yvasyliev.dz4j.service.EditorialService;
import io.github.yvasyliev.dz4j.service.EpisodeService;
import io.github.yvasyliev.dz4j.service.GenreService;
import io.github.yvasyliev.dz4j.service.InfosService;
import io.github.yvasyliev.dz4j.service.OAuthService;
import io.github.yvasyliev.dz4j.service.OEmbedService;
import io.github.yvasyliev.dz4j.service.OptionsService;
import io.github.yvasyliev.dz4j.service.PlaylistService;
import io.github.yvasyliev.dz4j.service.PodcastService;
import io.github.yvasyliev.dz4j.service.RadioService;
import io.github.yvasyliev.dz4j.service.SearchService;
import io.github.yvasyliev.dz4j.service.TrackService;
import io.github.yvasyliev.dz4j.service.UploadService;
import io.github.yvasyliev.dz4j.service.UserService;
import io.github.yvasyliev.dz4j.util.TokenManagers;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Provider of request factories for different Deezer API endpoints.
 */
@Getter
@Accessors(fluent = true)
@SuppressWarnings({"checkstyle:ClassDataAbstractionCoupling", "checkstyle:ClassFanOutComplexity"})
public class RequestFactoryProvider {
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

    /**
     * Constructs a new {@link RequestFactoryProvider}.
     *
     * @param builder             the Feign async builder to use for creating service clients
     * @param baseUrls            the base URLs for the Deezer API endpoints
     * @param accessTokenSupplier an access token supplier
     */
    public RequestFactoryProvider(
            AsyncFeign.AsyncBuilder<Object> builder,
            BaseUrls baseUrls,
            Supplier<CompletableFuture<AccessToken>> accessTokenSupplier
    ) {
        var assembler = new RequestFactoryAssembler(
                builder,
                baseUrls.api(),
                TokenManagers.accessTokenManager(accessTokenSupplier)
        );

        album = assembler.assemble(AlbumRequestFactory::new, AlbumService.class);
        artist = assembler.assemble(ArtistRequestFactory::new, ArtistService.class);
        chart = assembler.assemble(ChartRequestFactory::new, ChartService.class);
        editorial = assembler.assemble(EditorialRequestFactory::new, EditorialService.class);
        episode = assembler.assemble(EpisodeRequestFactory::new, EpisodeService.class);
        genre = assembler.assemble(GenreRequestFactory::new, GenreService.class);
        infos = assembler.assemble(InfosRequestFactory::new, InfosService.class);
        oauth = assembler.assemble(OAuthRequestFactory::new, OAuthService.class, baseUrls.oauth());
        oEmbed = assembler.assemble(OEmbedRequestFactory::new, OEmbedService.class);
        options = assembler.assemble(OptionsRequestFactory::new, OptionsService.class);
        playlist = assembler.assemble(PlaylistRequestFactory::new, PlaylistService.class);
        podcast = assembler.assemble(PodcastRequestFactory::new, PodcastService.class);
        radio = assembler.assemble(RadioRequestFactory::new, RadioService.class);
        search = assembler.assemble(SearchRequestFactory::new, SearchService.class);
        track = assembler.assemble(TrackRequestFactory::new, TrackService.class);
        upload = assembler.assemble(infos, baseUrls.upload());
        user = assembler.assemble(UserRequestFactory::new, UserService.class);
    }

    @RequiredArgsConstructor
    private static class RequestFactoryAssembler {
        private final AsyncFeign.AsyncBuilder<Object> builder;
        private final String baseUrl;
        private final TokenManager<AccessToken> accessTokenManager;

        private <T, R> R assemble(Function<T, R> factory, Class<T> target) {
            return assemble(factory, target, baseUrl);
        }

        private <T, R> R assemble(BiFunction<T, TokenManager<AccessToken>, R> factory, Class<T> target) {
            return assemble(t -> factory.apply(t, accessTokenManager), target);
        }

        private UploadRequestFactory assemble(InfosRequestFactory infos, String url) {
            return assemble(
                    t -> new UploadRequestFactory(t, accessTokenManager, TokenManagers.uploadTokenManager(infos)),
                    UploadService.class,
                    url
            );
        }

        private <T, R> R assemble(Function<T, R> factory, Class<T> target, String url) {
            return factory.apply(builder.target(target, url));
        }
    }
}
