package io.github.yvasyliev.deezer.request;

import io.github.yvasyliev.deezer.exception.DeezerException;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("checkstyle:AbstractClassName")
class AbstractDeezerRequestTest {
    @Mock private Supplier<CompletableFuture<String>> asyncMethod;
    private DeezerRequest<String> request;

    @BeforeEach
    void setUp() {
        request = new AbstractDeezerRequest<>() {
            @Override
            @NonNull
            protected CompletableFuture<String> doExecuteAsync() {
                return asyncMethod.get();
            }
        };
    }

    @Test
    void shouldExecuteSuccessfully() {
        var expected = "result";

        when(asyncMethod.get()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = request.execute();

        assertEquals(expected, actual);
    }

    @Test
    void shouldExecuteAsyncSuccessfully() {
        var expected = "result";

        when(asyncMethod.get()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = request.executeAsync().join();

        assertEquals(expected, actual);
    }

    @Test
    void shouldRethrowDeezerException() {
        when(asyncMethod.get()).thenReturn(CompletableFuture.failedFuture(mock(DeezerException.class)));

        assertThrows(DeezerException.class, request::execute);
    }

    @Test
    void shouldRethrowDeezerExceptionAsync() {
        when(asyncMethod.get()).thenReturn(CompletableFuture.failedFuture(mock(DeezerException.class)));

        var e = assertThrows(CompletionException.class, () -> request.executeAsync().join());

        assertThat(e).hasCauseInstanceOf(DeezerException.class);
    }

    @Test
    void shouldRethrowDeezerExceptionCause() {
        when(asyncMethod.get()).thenReturn(CompletableFuture.failedFuture(new Exception(new DeezerException("boom"))));

        assertThrows(DeezerException.class, request::execute);
    }

    @Test
    void shouldRethrowDeezerExceptionCauseAsync() {
        when(asyncMethod.get()).thenReturn(CompletableFuture.failedFuture(new Exception(new DeezerException("boom"))));

        var e = assertThrows(CompletionException.class, () -> request.executeAsync().join());

        assertThat(e).hasCauseInstanceOf(DeezerException.class);
    }

    @Test
    void shouldWrapOtherExceptions() {
        when(asyncMethod.get()).thenReturn(CompletableFuture.failedFuture(new Exception("boom")));

        assertThrows(DeezerException.class, request::execute);
    }

    @Test
    void shouldWrapOtherExceptionsAsync() {
        when(asyncMethod.get()).thenReturn(CompletableFuture.failedFuture(new Exception("boom")));

        var e = assertThrows(CompletionException.class, () -> request.executeAsync().join());

        assertThat(e).hasCauseInstanceOf(DeezerException.class);
    }
}
