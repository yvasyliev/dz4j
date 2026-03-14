package io.github.yvasyliev.deezer.util;

import feign.AsyncFeign;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RequestFactoryProviderTest {
    @Mock private AsyncFeign.AsyncBuilder<Object> builder;
    @Mock private Supplier<CompletableFuture<AccessToken>> accessTokenSupplier;

    @Test
    void testRequestFactoryProvider() {
        var apiBaseUrl = "https://api.example.com";
        var oauthBaseUrl = "https://oauth.example.com";
        var uploadBaseUrl = "https://upload.example.com";
        var requestFactoryProvider = new RequestFactoryProvider(
                builder,
                apiBaseUrl,
                oauthBaseUrl,
                uploadBaseUrl,
                accessTokenSupplier
        );

        assertThat(requestFactoryProvider).hasNoNullFieldsOrProperties();
        verify(builder).target(AlbumService.class, apiBaseUrl);
        verify(builder).target(ArtistService.class, apiBaseUrl);
        verify(builder).target(ChartService.class, apiBaseUrl);
        verify(builder).target(EditorialService.class, apiBaseUrl);
        verify(builder).target(EpisodeService.class, apiBaseUrl);
        verify(builder).target(GenreService.class, apiBaseUrl);
        verify(builder).target(InfosService.class, apiBaseUrl);
        verify(builder).target(OEmbedService.class, apiBaseUrl);
        verify(builder).target(OptionsService.class, apiBaseUrl);
        verify(builder).target(PlaylistService.class, apiBaseUrl);
        verify(builder).target(PodcastService.class, apiBaseUrl);
        verify(builder).target(RadioService.class, apiBaseUrl);
        verify(builder).target(SearchService.class, apiBaseUrl);
        verify(builder).target(TrackService.class, apiBaseUrl);
        verify(builder).target(UserService.class, apiBaseUrl);
        verify(builder).target(OAuthService.class, oauthBaseUrl);
        verify(builder).target(UploadService.class, uploadBaseUrl);
    }
}

