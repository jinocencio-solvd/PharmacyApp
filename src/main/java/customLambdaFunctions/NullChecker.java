package customLambdaFunctions;

public interface NullChecker<T> {
    boolean isNull(T obj);
}
