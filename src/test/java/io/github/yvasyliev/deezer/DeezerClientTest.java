package io.github.yvasyliev.deezer;

import feign.AsyncFeign;
import io.github.yvasyliev.deezer.authorization.AccessTokenSupplier;
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
import io.github.yvasyliev.deezer.service.OAuthService;
import io.github.yvasyliev.deezer.util.FeignConfigurator;
import io.github.yvasyliev.deezer.util.RequestFactoryProvider;
import io.github.yvasyliev.deezer.util.TriFunction;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.type;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeezerClientTest {
    @SuppressWarnings("unchecked")
    private static final Function<DeezerClient, Object>[] EXTRACTORS = List.<Function<DeezerClient, Object>>of(
            DeezerClient::album,
            DeezerClient::artist,
            DeezerClient::chart,
            DeezerClient::editorial,
            DeezerClient::episode,
            DeezerClient::genre,
            DeezerClient::infos,
            DeezerClient::oauth,
            DeezerClient::oEmbed,
            DeezerClient::options,
            DeezerClient::playlist,
            DeezerClient::podcast,
            DeezerClient::radio,
            DeezerClient::search,
            DeezerClient::track,
            DeezerClient::upload,
            DeezerClient::user
    ).toArray(Function[]::new);

    private static final Supplier<Stream<Arguments>> shouldCreateDefaultDeezerClient = () -> Stream.of(
            arguments(new DeezerClient()),
            arguments(DeezerClient.builder().build())
    );

    private static final Supplier<Stream<Arguments>> shouldSetAuthorization = () -> {
        var token = "token";
        var nullAccessToken = new AccessToken(null, Instant.MAX);
        var staticAccessToken = new AccessToken(token, Instant.MAX);
        var tempAccessToken = new AccessToken(token, Instant.now());
        var stringAccessTokenClient = new DeezerClient();
        var accessTokenClient = new DeezerClient();
        var unauthorizedClient = new DeezerClient();

        stringAccessTokenClient.authorization(token);
        accessTokenClient.authorization(staticAccessToken);
        unauthorizedClient.authorization(token);
        unauthorizedClient.removeAuthorization();

        return Stream.of(
                arguments(new DeezerClient(), nullAccessToken),
                arguments(DeezerClient.builder().build(), nullAccessToken),
                arguments(new DeezerClient(token), staticAccessToken),
                arguments(stringAccessTokenClient, staticAccessToken),
                arguments(DeezerClient.builder().authorization(token).build(), staticAccessToken),
                arguments(new DeezerClient(staticAccessToken), staticAccessToken),
                arguments(accessTokenClient, staticAccessToken),
                arguments(DeezerClient.builder().authorization(staticAccessToken).build(), staticAccessToken),
                arguments(withAppAuthorization(DeezerClient::new, tempAccessToken), tempAccessToken),
                arguments(
                        withAppAuthorization(
                                (appId, secret, code) -> {
                                    var client = new DeezerClient();
                                    client.authorization(appId, secret, code);
                                    return client;
                                },
                                tempAccessToken
                        ),
                        tempAccessToken
                ),
                arguments(
                        withAppAuthorization(
                                (appId, secret, code) -> DeezerClient.builder()
                                        .authorization(appId, secret, code)
                                        .build(),
                                tempAccessToken
                        ),
                        tempAccessToken
                ),
                arguments(unauthorizedClient, nullAccessToken)
        );
    };

    @Mock private RequestFactoryProvider requestFactoryProvider;
    @Mock private AccessTokenSupplier accessTokenSupplier;

    @ParameterizedTest
    @FieldSource
    void shouldCreateDefaultDeezerClient(DeezerClient deezerClient) {
        assertThat(deezerClient)
                .extracting(EXTRACTORS)
                .doesNotContainNull();
    }

    @Test
    void shouldReturnRequestFactories() {
        var album = mock(AlbumRequestFactory.class);
        var artist = mock(ArtistRequestFactory.class);
        var chart = mock(ChartRequestFactory.class);
        var editorial = mock(EditorialRequestFactory.class);
        var episode = mock(EpisodeRequestFactory.class);
        var genre = mock(GenreRequestFactory.class);
        var infos = mock(InfosRequestFactory.class);
        var oauth = mock(OAuthRequestFactory.class);
        var oEmbed = mock(OEmbedRequestFactory.class);
        var options = mock(OptionsRequestFactory.class);
        var playlist = mock(PlaylistRequestFactory.class);
        var podcast = mock(PodcastRequestFactory.class);
        var radio = mock(RadioRequestFactory.class);
        var search = mock(SearchRequestFactory.class);
        var track = mock(TrackRequestFactory.class);
        var upload = mock(UploadRequestFactory.class);
        var user = mock(UserRequestFactory.class);

        when(requestFactoryProvider.album()).thenReturn(album);
        when(requestFactoryProvider.artist()).thenReturn(artist);
        when(requestFactoryProvider.chart()).thenReturn(chart);
        when(requestFactoryProvider.editorial()).thenReturn(editorial);
        when(requestFactoryProvider.episode()).thenReturn(episode);
        when(requestFactoryProvider.genre()).thenReturn(genre);
        when(requestFactoryProvider.infos()).thenReturn(infos);
        when(requestFactoryProvider.oauth()).thenReturn(oauth);
        when(requestFactoryProvider.oEmbed()).thenReturn(oEmbed);
        when(requestFactoryProvider.options()).thenReturn(options);
        when(requestFactoryProvider.playlist()).thenReturn(playlist);
        when(requestFactoryProvider.podcast()).thenReturn(podcast);
        when(requestFactoryProvider.radio()).thenReturn(radio);
        when(requestFactoryProvider.search()).thenReturn(search);
        when(requestFactoryProvider.track()).thenReturn(track);
        when(requestFactoryProvider.upload()).thenReturn(upload);
        when(requestFactoryProvider.user()).thenReturn(user);

        var deezerClient = new DeezerClient(requestFactoryProvider, accessTokenSupplier);

        assertThat(deezerClient)
                .extracting(EXTRACTORS)
                .containsExactly(
                        album,
                        artist,
                        chart,
                        editorial,
                        episode,
                        genre,
                        infos,
                        oauth,
                        oEmbed,
                        options,
                        playlist,
                        podcast,
                        radio,
                        search,
                        track,
                        upload,
                        user
                );
    }

    @ParameterizedTest
    @FieldSource
    void shouldSetAuthorization(DeezerClient deezerClient, AccessToken expected) {
        assertThat(deezerClient)
                .extracting("accessTokenSupplier", type(AccessTokenSupplier.class))
                .extracting(AccessTokenSupplier::get)
                .extracting(CompletableFuture::join)
                .isEqualTo(expected);
    }

    private static DeezerClient withAppAuthorization(
            TriFunction<Integer, String, String, DeezerClient> factory,
            AccessToken accessToken
    ) {
        var appId = 123;
        var secret = "secret";
        var code = "code";
        var builder = Mockito.<AsyncFeign.AsyncBuilder<Object>>mock();
        var oAuthService = mock(OAuthService.class);
        @Cleanup var feignConfigurator = mockStatic(FeignConfigurator.class);

        feignConfigurator.when(() -> FeignConfigurator.build(any(), any(), any(), any())).thenReturn(builder);
        lenient().when(builder.target(eq(OAuthService.class), anyString())).thenReturn(oAuthService);
        when(oAuthService.getAccessTokenAsync(appId, secret, code))
                .thenReturn(CompletableFuture.completedFuture(accessToken));

        return factory.apply(appId, secret, code);
    }
}
