package customLambdaFunctions;

public interface IRepeater<T> {

    void repeat(int numRepeats, IPerformOperation<T> operation);
}
