package io.github.yvasyliev.deezer.util;

import org.jspecify.annotations.Nullable;

/**
 * Represents a function that accepts five arguments and produces a result.
 *
 * @param <A> the type of the first argument to the function
 * @param <B> the type of the second argument to the function
 * @param <C> the type of the third argument to the function
 * @param <D> the type of the fourth argument to the function
 * @param <E> the type of the fifth argument to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface QuinaryFunction<A, B, C, D, E, R> {
    /**
     * Applies this function to the given arguments.
     *
     * @param a the first argument to the function
     * @param b the second argument to the function
     * @param c the third argument to the function
     * @param d the fourth argument to the function
     * @param e the fifth argument to the function
     * @return the result of applying this function to the given arguments
     */
    R apply(A a, @Nullable B b, @Nullable C c, @Nullable D d, @Nullable E e);
}
