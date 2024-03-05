package ali.su.cft2j02.middleworks;

@FunctionalInterface
public interface MiddleWorker<T> {
    T doWork(T t);
}
