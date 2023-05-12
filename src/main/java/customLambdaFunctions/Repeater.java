package customLambdaFunctions;

public interface Repeater<T> {

    void repeat(int numRepeats, PerformOperation<T> operation);
}
