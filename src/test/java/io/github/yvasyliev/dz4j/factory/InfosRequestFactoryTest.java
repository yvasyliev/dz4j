package io.github.yvasyliev.dz4j.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import io.github.yvasyliev.dz4j.authorization.AuthorizationManager;
import io.github.yvasyliev.dz4j.model.AccessToken;
import io.github.yvasyliev.dz4j.model.Infos;
import io.github.yvasyliev.dz4j.service.InfosService;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InfosRequestFactoryTest {
    @InjectMocks
    private InfosRequestFactory infosRequestFactory;

    @Mock
    private InfosService infosService;

    @Mock
    private AuthorizationManager authorizationManager;

    @Test
    void testGetInfos() {
        var accessToken = new AccessToken("test-token");
        var expected = Infos.builder().countryIso("US").build();

        when(authorizationManager.getToken()).thenReturn(CompletableFuture.completedFuture(accessToken));
        when(infosService.getInfos(accessToken)).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = infosRequestFactory.getInfos().execute();

        assertEquals(expected, actual);
    }
}
