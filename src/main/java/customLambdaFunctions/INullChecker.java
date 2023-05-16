package customLambdaFunctions;

public interface INullChecker<T> {
    boolean isNull(T obj);
}
