package io.github.yvasyliev.deezer.util;

@FunctionalInterface
public interface QuinaryFunction<A, B, C, D, E, R> {
    R apply(A a, B b, C c, D d, E e);
}
