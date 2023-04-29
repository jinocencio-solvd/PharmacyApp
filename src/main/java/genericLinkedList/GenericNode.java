package genericLinkedList;

public class GenericNode<T> {

    protected T data;
    protected GenericNode<T> next;

    public GenericNode(T data) {
        this.data = data;
        this.next = null;
    }
}
