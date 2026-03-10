package io.github.yvasyliev.deezer.factory;

import io.github.yvasyliev.deezer.authorization.TokenManager;
import io.github.yvasyliev.deezer.model.AccessToken;
import io.github.yvasyliev.deezer.model.Infos;
import io.github.yvasyliev.deezer.service.InfosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfosRequestFactoryTest {
    @InjectMocks private InfosRequestFactory infosRequestFactory;
    @Mock private InfosService infosService;
    @Mock private TokenManager<AccessToken> accessTokenManager;

    @Test
    void testGetInfos() {
        var token = "test-token";
        var expected = Infos.builder().countryIso("US").build();

        when(accessTokenManager.getToken()).thenReturn(CompletableFuture.completedFuture(token));
        when(infosService.getInfos(token)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = infosRequestFactory.getInfos().execute();

        assertEquals(expected, actual);
    }
}

