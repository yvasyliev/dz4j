package io.github.yvasyliev.dz4j.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import io.github.yvasyliev.dz4j.model.Options;
import io.github.yvasyliev.dz4j.service.OptionsService;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OptionsRequestFactoryTest {
    @InjectMocks
    private OptionsRequestFactory optionsRequestFactory;

    @Mock
    private OptionsService optionsService;

    @Test
    void testGetOptions() {
        var expected =
                Options.builder().streaming(true).offline(true).radioSkips(10).build();

        when(optionsService.getOptions()).thenReturn(CompletableFuture.completedFuture(expected));

        var actual = optionsRequestFactory.getOptions().execute();

        assertEquals(expected, actual);
    }
}
