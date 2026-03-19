package io.github.yvasyliev.deezer.util;

import lombok.experimental.UtilityClass;

import java.util.function.Consumer;

@UtilityClass
public class Customizer {
    public static <T> T customize(T t, Consumer<T> customizer) {
        if (customizer != null) {
            customizer.accept(t);
        }

        return t;
    }
}
