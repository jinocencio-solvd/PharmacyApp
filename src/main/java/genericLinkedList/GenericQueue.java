package genericLinkedList;

public class GenericQueue<T> extends GenericLinkedList<T> {

    public void enqueue(T data){
        addLast(data);
    }

    public T dequeue(){
        T retElement = getFirst();
        removeFirst();
        return retElement;
    }

    public T peek(){
        return getFirst();
    }
}
