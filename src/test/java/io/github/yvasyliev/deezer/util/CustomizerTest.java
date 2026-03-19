package io.github.yvasyliev.deezer.util;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CustomizerTest {
    @Test
    void shouldApplyCustomizer() {
        var expected = mock();
        var customizer = Mockito.<Consumer<Object>>mock();
        var actual = Customizer.customize(expected, customizer);

        assertEquals(expected, actual);
        verify(customizer).accept(expected);
    }

    @Test
    void shouldNotApplyCustomizerIfCustomizerIsNull() {
        var expected = mock();
        var a = Customizer.customize(expected, null);

        assertEquals(expected, a);
    }
}