package linkedList;

public interface IlinkedList<T> {

    /**
     * Adds an item to the beginning of the list
     */
    void addFirst(T data);

    /**
     * Add an item to the end of the list
     */
    void addLast(T data);

    /**
     * Remove an item from the beginning of the list
     */
    void removeFirst();

    /**
     * Remove an item from the end of the list
     */
    void removeLast();

    /**
     * Get the item at the beginning of the list
     */
    T getFirst();

    /**
     * Get the item at the end of the list
     */
    T getLast();

    /**
     * Get size of linkedList
     * @return size of linkedList
     */
    int size();
    /**
     * Return a string representation of the LinkedList
     */
    String printList();
}