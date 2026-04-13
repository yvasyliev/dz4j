package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.model.Options;
import io.github.yvasyliev.dz4j.service.OptionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OptionsRequestFactoryTest {
    @InjectMocks private OptionsRequestFactory optionsRequestFactory;
    @Mock private OptionsService optionsService;

    @Test
    void testGetOptions() {
        var expected = Options.builder()
                .streaming(true)
                .offline(true)
                .radioSkips(10)
                .build();

        when(optionsService.getOptions()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = optionsRequestFactory.getOptions().execute();

        assertEquals(expected, actual);
    }
}
