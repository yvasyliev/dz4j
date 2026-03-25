package io.github.yvasyliev.deezer.authorization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenManagerTest {
    @InjectMocks private TokenManager<Object> tokenManager;
    @Mock private Predicate<CompletableFuture<Object>> tokenValidator;
    @Mock private Supplier<CompletableFuture<Object>> tokenSupplier;
    @Mock private Function<Object, String> tokenMapper;

    @Test
    void shouldReturnANewTokenForTheFirstTime() {
        var token = mock(Object.class);
        var expected = "expected";

        when(tokenSupplier.get()).thenReturn(CompletableFuture.completedFuture(token));
        when(tokenMapper.apply(token)).thenReturn(expected);

        var actual = tokenManager.getToken();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void shouldReturnANewTokenWhenTheOldTokenIsInvalid() {
        var invalidTokenFuture = CompletableFuture.completedFuture(mock(Object.class));
        var validToken = mock(Object.class);
        var expected = "expected";

        when(tokenSupplier.get()).thenReturn(invalidTokenFuture);

        assertThat(tokenManager.getToken()).isCompleted();

        when(tokenSupplier.get()).thenReturn(CompletableFuture.completedFuture(validToken));
        when(tokenMapper.apply(validToken)).thenReturn(expected);

        var actual = tokenManager.getToken();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
    }

    @Test
    void shouldReturnExistingToken() {
        var token = mock(Object.class);
        var tokenFuture = CompletableFuture.completedFuture(token);
        var expected = "expected";

        when(tokenSupplier.get()).thenReturn(tokenFuture);

        assertThat(tokenManager.getToken()).isCompleted();

        when(tokenValidator.test(tokenFuture)).thenReturn(true);
        when(tokenMapper.apply(token)).thenReturn(expected);

        var actual = tokenManager.getToken();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
        verify(tokenSupplier, times(1)).get();
    }

    @Test
    void shouldReturnExistingTokenWhenItWasFetchConcurrently() {
        var token = mock(Object.class);
        var tokenFuture = CompletableFuture.completedFuture(token);
        var expected = "expected";
        var validationCount = new AtomicInteger(0);

        when(tokenSupplier.get()).thenReturn(tokenFuture);

        assertThat(tokenManager.getToken()).isCompleted();

        when(tokenValidator.test(tokenFuture)).thenAnswer(invocation -> validationCount.getAndIncrement() != 0);
        when(tokenMapper.apply(token)).thenReturn(expected);

        var actual = tokenManager.getToken();

        assertThat(actual).succeedsWithin(Duration.ofSeconds(1)).isEqualTo(expected);
        verify(tokenSupplier, times(1)).get();
    }
}
