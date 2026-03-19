package io.github.yvasyliev.deezer.util;

import feign.AsyncFeign;
import io.github.yvasyliev.deezer.authorization.TokenManager;
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
import io.github.yvasyliev.deezer.model.AccessToken;
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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

//TODO: what package fits best for this class?
@Getter
@Accessors(fluent = true)
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

        private UploadRequestFactory assemble(InfosRequestFactory infos, String baseUrl) {
            return assemble(
                    t -> new UploadRequestFactory(t, accessTokenManager, TokenManagers.uploadTokenManager(infos)),
                    UploadService.class,
                    baseUrl
            );
        }

        private <T, R> R assemble(Function<T, R> factory, Class<T> target, String baseUrl) {
            return factory.apply(builder.target(target, baseUrl));
        }
    }
}
