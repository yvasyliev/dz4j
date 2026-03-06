package io.github.yvasyliev.deezer;

import feign.AsyncFeign;
import io.github.yvasyliev.deezer.authorization.AccessTokenSupplier;
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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
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
        this((AccessToken) null);
    }

    public DeezerClient(String accessToken) {
        this(new AccessToken(accessToken, Instant.MAX));
    }

    public DeezerClient(AccessToken accessToken) {
        this(oauth -> CompletableFuture.completedFuture(accessToken));
    }

    public DeezerClient(int appId, String secret, String code) {
        this(oauth -> oauth.getAccessToken(appId, secret, code).executeAsync());
    }

    private DeezerClient(Function<OAuthRequestFactory, CompletableFuture<AccessToken>> accessTokenProvider) {
        var deezerApi = "https://api.deezer.com";
        var builder = AsyncFeign.builder();

        album = new AlbumRequestFactory(builder.target(AlbumService.class, deezerApi));
        artist = new ArtistRequestFactory(builder.target(ArtistService.class, deezerApi));
        oauth = new OAuthRequestFactory(builder.target(OAuthService.class, deezerApi));
        chart = new ChartRequestFactory(builder.target(ChartService.class, deezerApi));
        editorial = new EditorialRequestFactory(builder.target(EditorialService.class, deezerApi));
        genre = new GenreRequestFactory(builder.target(GenreService.class, deezerApi));
        oEmbed = new OEmbedRequestFactory(builder.target(OEmbedService.class, deezerApi));
        options = new OptionsRequestFactory(builder.target(OptionsService.class, deezerApi));
        radio = new RadioRequestFactory(builder.target(RadioService.class, deezerApi));
        search = new SearchRequestFactory(builder.target(SearchService.class, deezerApi));

        accessTokenSupplier = new AccessTokenSupplier(() -> accessTokenProvider.apply(oauth));

        var accessTokenManager = new TokenManager<>(token -> true, accessTokenSupplier, AccessToken::token);

        episode = new EpisodeRequestFactory(builder.target(EpisodeService.class, deezerApi), accessTokenManager);
        infos = new InfosRequestFactory(builder.target(InfosService.class, deezerApi), accessTokenManager);
        playlist = new PlaylistRequestFactory(builder.target(PlaylistService.class, deezerApi), accessTokenManager);
        podcast = new PodcastRequestFactory(builder.target(PodcastService.class, deezerApi), accessTokenManager);
        track = new TrackRequestFactory(builder.target(TrackService.class, deezerApi), accessTokenManager);
        user = new UserRequestFactory(builder.target(UserService.class, deezerApi), accessTokenManager);

        var uploadTokenManager = new TokenManager<>(
                token -> token.isDone() && !token.isCompletedExceptionally() && !token.join().isUploadTokenExpired(),
                () -> infos.getInfos().executeAsync(),
                Infos::uploadToken
        );

        upload = new UploadRequestFactory(
                builder.target(UploadService.class, deezerApi),
                accessTokenManager,
                uploadTokenManager
        );
    }
}
