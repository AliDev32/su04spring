package ali.su.cft2j02.datareader;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface MapperSourceToData<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    R apply(T t1, T t2);
}
