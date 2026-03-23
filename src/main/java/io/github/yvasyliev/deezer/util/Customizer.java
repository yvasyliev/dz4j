package io.github.yvasyliev.deezer.util;

import lombok.experimental.UtilityClass;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

/**
 * A utility class for customizing objects using a fluent API.
 */
@UtilityClass
public class Customizer {
    /**
     * Customizes an object using the provided customizer function and returns the customized object.
     *
     * @param t          the object to customize
     * @param customizer a function that takes the object and customizes it
     * @param <T>        the type of the object being customized
     * @return the customized object
     */
    public static <T> T customize(T t, @Nullable Consumer<T> customizer) {
        if (customizer != null) {
            customizer.accept(t);
        }

        return t;
    }
}
