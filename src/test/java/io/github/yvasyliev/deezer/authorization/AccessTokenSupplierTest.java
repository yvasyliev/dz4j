package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.model.AccessToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccessTokenSupplierTest {
    @InjectMocks private AccessTokenSupplier accessTokenSupplier;
    @Mock private AccessTokenProvider accessTokenProvider;

    @Test
    void testGet() {
        testGet(accessTokenProvider);
    }

    @Test
    void testSetAccessTokenProvider() {
        var accessTokenProvider = mock(AccessTokenProvider.class);

        accessTokenSupplier.setAccessTokenProvider(accessTokenProvider);

        testGet(accessTokenProvider);
    }

    private void testGet(AccessTokenProvider accessTokenProvider) {
        var expected = CompletableFuture.completedFuture(mock(AccessToken.class));

        when(accessTokenProvider.getAccessToken()).thenReturn(expected);

        var actual = accessTokenSupplier.get();

        assertEquals(expected, actual);
    }
}