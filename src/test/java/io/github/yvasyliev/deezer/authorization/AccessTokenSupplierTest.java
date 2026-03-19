package io.github.yvasyliev.deezer.authorization;

import io.github.yvasyliev.deezer.model.AccessToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccessTokenSupplierTest {
    @Mock private Supplier<CompletableFuture<AccessToken>> delegate;

    @Test
    void shouldGetAccessToken() {
        var accessTokenSupplier = new AccessTokenSupplier();
        var expected = new AccessToken("token", Instant.now());

        when(delegate.get()).thenReturn(CompletableFuture.completedFuture(expected));

        accessTokenSupplier.setDelegate(delegate);

        var actual = accessTokenSupplier.get();

        assertThat(actual).isCompletedWithValue(expected);
    }
}
