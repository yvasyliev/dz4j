package io.github.yvasyliev.deezer.util;

import org.jspecify.annotations.NullUnmarked;

/**
 * Represents a function that accepts four arguments and produces a result.
 *
 * @param <A> the type of the first argument to the function
 * @param <B> the type of the second argument to the function
 * @param <C> the type of the third argument to the function
 * @param <D> the type of the fourth argument to the function
 * @param <R> the type of the result of the function
 */
@NullUnmarked
@FunctionalInterface
public interface QuadFunction<A, B, C, D, R> {
    /**
     * Applies this function to the given arguments.
     *
     * @param a the first argument
     * @param b the second argument
     * @param c the third argument
     * @param d the fourth argument
     * @return the result of applying this function to the given arguments
     */
    R apply(A a, B b, C c, D d);
}
